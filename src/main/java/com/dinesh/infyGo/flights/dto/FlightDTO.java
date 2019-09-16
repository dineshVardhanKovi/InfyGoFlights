package com.dinesh.infyGo.flights.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class FlightDTO {

	private String flightId;

	private String source;

	private String destination;

	private String airlines;

	private Double fare;
	
	private Date flightAvailableDate;
	
	private int seatCount;
	
	private String departureTime;
	
	private String arrivalTime;
}
