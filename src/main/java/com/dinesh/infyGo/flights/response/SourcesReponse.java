package com.dinesh.infyGo.flights.response;

import java.util.List;

import lombok.Data;

@Data
public class SourcesReponse {
	private List<String> sources;

	public SourcesReponse(List<String> sources) {
		super();
		this.sources = sources;
	}
	
	
}
