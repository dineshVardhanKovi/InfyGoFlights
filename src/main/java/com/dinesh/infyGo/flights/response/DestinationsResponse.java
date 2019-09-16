package com.dinesh.infyGo.flights.response;

import java.util.List;

import lombok.Data;

@Data
public class DestinationsResponse {
	private List<String> destinations;

	public DestinationsResponse(List<String> destinations) {
		super();
		this.destinations = destinations;
	}
	
	
}
