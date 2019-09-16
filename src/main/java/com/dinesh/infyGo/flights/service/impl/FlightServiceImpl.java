package com.dinesh.infyGo.flights.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.infyGo.flights.dto.FlightDTO;
import com.dinesh.infyGo.flights.entity.Flight;
import com.dinesh.infyGo.flights.exception.FlightNotFoundException;
import com.dinesh.infyGo.flights.repository.FlightRepository;
import com.dinesh.infyGo.flights.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private DozerBeanMapper beanMapper;
	
	@Override
	public List<String> getSources() {
		List<String> sources =  new ArrayList<>();;
		try {
			sources = flightRepository.getAllSources();
		} catch (Exception e) {
			log.error("Exception occured in FlightServiceImpl.getSources(): " + e.getMessage());
			throw e;
		}
		return sources;
	}

	@Override
	public List<String> getDestinations() {
		List<String> destinations =  new ArrayList<>();;
		try {
			destinations = flightRepository.getAllDestinations();
		} catch (Exception e) {
			log.error("Exception occured in FlightServiceImpl.getDestinations(): " + e.getMessage());
			throw e;
		}
		return destinations;
	}

	@Override
	public List<FlightDTO> getAllFLights() {
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		try {
			flightRepository.findAll().forEach(flight ->{
				FlightDTO flightDTO = beanMapper.map(flight, FlightDTO.class);
				flights.add(flightDTO);
			});
		} catch (Exception e) {
			log.error("Exception occured in FlightServiceImpl.getAllFLights(): " + e.getMessage());
			throw e;
		}		
		return flights;
	}

	@Override
	public FlightDTO getFlightDetails(String flightId) {
		FlightDTO flight = new FlightDTO();
		try {
			Optional<Flight> optinalFlight = flightRepository.findById(flightId);
			if(optinalFlight.isPresent()) {
				flight = beanMapper.map(optinalFlight.get(), FlightDTO.class);
			}
		} catch (Exception e) {
			log.error("Exception occured in FlightServiceImpl.getFlightDetails(): " + e.getMessage());
			throw e;
		}
		return flight;
	}

	@Override
	public List<FlightDTO> getFlightDetails(String source, String destination, Date travelDate) {
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		try {
			flightRepository.findBySourceAndDestinationAndFlightAvailableDate(source, destination, travelDate).forEach(flight ->{
				FlightDTO flightDTO = beanMapper.map(flight,FlightDTO.class);
				flights.add(flightDTO);
			});
		} catch (Exception e) {
			log.error("Exception occured in FlightServiceImpl.getFlightDetails(): " + e.getMessage());
			throw e;
		}
		return flights;
	}

	@Override
	public void updateNoOfSeats(String flightId, int noOfSeats) throws FlightNotFoundException{
		Optional<Flight> optinalFlight = flightRepository.findById(flightId);
		if(optinalFlight.isPresent()) {
			Flight flight = optinalFlight.get();
			flight.setSeatCount(flight.getSeatCount() - noOfSeats);
			flightRepository.save(flight);
		}else {
			log.error("Flight Not Found ");
			throw new FlightNotFoundException("Flight not found with id : " + flightId);
		}
	}

}
