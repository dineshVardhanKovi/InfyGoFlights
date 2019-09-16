package com.dinesh.infyGo.flights.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "FLIGHT_DETAILS")
@Data
public class Flight {

	@Id
	@Column(name = "flight_id")
	private String flightId;
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "airlines")
	private String airlines;
	
	@Column(name = "fare")
	private Double fare;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "flight_available_date")
	private Date flightAvailableDate;
	
	@Column(name = "seat_count")
	private int seatCount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "departure_time")
	private Date departureTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "arrival_time")
	private Date arrivalTime;
}
