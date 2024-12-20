package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.dto.FlightDTO;
import com.cts.model.Flight;

public interface FlightService {

	public ResponseEntity<String> createflight(FlightDTO flightdto);
	public ResponseEntity<String> updateflight(String flightid,FlightDTO flightdto);
	public ResponseEntity<String> deleteflight(String flightid);
	public Optional<Flight> findflightById(String flightid);
	public List<Flight> findAllflight();
	public Flight findFlightBySourceAndDesitnation(String source, String desination);
	
}
