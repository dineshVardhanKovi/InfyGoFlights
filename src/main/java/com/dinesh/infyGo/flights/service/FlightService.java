package com.dinesh.infyGo.flights.service;

import java.sql.Date;
import java.util.List;

import com.dinesh.infyGo.flights.dto.FlightDTO;
import com.dinesh.infyGo.flights.exception.FlightNotFoundException;

public interface FlightService {
	List<String> getSources();
	List<String> getDestinations();
	List<FlightDTO> getAllFLights();
	FlightDTO getFlightDetails(String flightId);
	List<FlightDTO> getFlightDetails(String source,String destination,Date travelDate);
	void updateNoOfSeats(String flightId,int noOfSeats) throws FlightNotFoundException;
}
