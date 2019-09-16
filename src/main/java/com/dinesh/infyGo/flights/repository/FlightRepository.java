package com.dinesh.infyGo.flights.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dinesh.infyGo.flights.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>{
	@Query("select source from Flight")
	List<String> getAllSources();
	
	@Query("select destination from Flight")
	List<String> getAllDestinations();
	
	List<Flight> findBySourceAndDestinationAndFlightAvailableDate(String source, String destination, Date travelDate);
}
