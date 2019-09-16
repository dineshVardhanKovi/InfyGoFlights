package com.dinesh.infyGo.flights.response;

import java.util.List;

import com.dinesh.infyGo.flights.dto.FlightDTO;

import lombok.Data;

@Data
public class FlightsResponse {
	private List<FlightDTO> flights;

	public FlightsResponse(List<FlightDTO> flights) {
		super();
		this.flights = flights;
	}
}
