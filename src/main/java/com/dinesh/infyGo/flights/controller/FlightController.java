package com.dinesh.infyGo.flights.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.infyGo.flights.constants.InfyGoFLightConstants;
import com.dinesh.infyGo.flights.dto.FlightDTO;
import com.dinesh.infyGo.flights.exception.FlightNotFoundException;
import com.dinesh.infyGo.flights.response.DestinationsResponse;
import com.dinesh.infyGo.flights.response.FlightsResponse;
import com.dinesh.infyGo.flights.response.SourcesReponse;
import com.dinesh.infyGo.flights.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value =InfyGoFLightConstants.BASE_PATH)
@Slf4j
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/sources")
	public ResponseEntity<SourcesReponse> getSources(){
		ResponseEntity<SourcesReponse>  responseEntity = null;

		try {
			responseEntity = new ResponseEntity<SourcesReponse>(new SourcesReponse(flightService.getSources()),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured in FlightController.getSources() : " + e.getMessage());
			responseEntity = new ResponseEntity<SourcesReponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/destinations")
	public ResponseEntity<DestinationsResponse> getDestinations(){
		ResponseEntity<DestinationsResponse>  responseEntity = null;

		try {
			responseEntity = new ResponseEntity<DestinationsResponse>(new DestinationsResponse(flightService.getDestinations()),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured in FlightController.getDestinations() : " + e.getMessage());
			responseEntity = new ResponseEntity<DestinationsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/flights")
	public ResponseEntity<FlightsResponse> getFlights(){
		ResponseEntity<FlightsResponse>  responseEntity = null;

		try {
			responseEntity = new ResponseEntity<FlightsResponse>(new FlightsResponse(flightService.getAllFLights()),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured in FlightController.getFlights() : " + e.getMessage());
			responseEntity = new ResponseEntity<FlightsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/flights/{flightId}")
	public ResponseEntity<FlightsResponse> getFlightDetails(@PathVariable("flightId")String flightId){
		ResponseEntity<FlightsResponse>  responseEntity = null;

		try {
			List<FlightDTO> flights= new ArrayList<FlightDTO>();
			flights.add(flightService.getFlightDetails(flightId));
			responseEntity = new ResponseEntity<FlightsResponse>(new FlightsResponse(flights),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured in FlightController.getFlightDetails() : " + e.getMessage());
			responseEntity = new ResponseEntity<FlightsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/flights/{source}/{destination}/{journeyDate}")
	public ResponseEntity<FlightsResponse> getFlightDetails(@PathVariable("source")String source,@PathVariable("destination")String destination,
			@PathVariable("journeyDate")Date journeyDate){
		ResponseEntity<FlightsResponse>  responseEntity = null;

		try {
			responseEntity = new ResponseEntity<FlightsResponse>(new FlightsResponse(flightService.getFlightDetails(source,destination,journeyDate)),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured in FlightController.getFlightDetails() : " + e.getMessage());
			responseEntity = new ResponseEntity<FlightsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PostMapping("/flights/{flightId}/{noOfSeats}")
	public ResponseEntity<String> updateNoOfSeats(@PathVariable("flightId")String flightId,@PathVariable("noOfSeats")int noOfSeats){
		ResponseEntity<String>  responseEntity = null;
		try {
			flightService.updateNoOfSeats(flightId, noOfSeats);
			responseEntity = new ResponseEntity<String>("Updated Succesfully",HttpStatus.OK);
		}catch (FlightNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Exception occured in FlightController.updateNoOfSeats() : " + e.getMessage());
			responseEntity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
