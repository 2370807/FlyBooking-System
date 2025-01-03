package com.cts.flybooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;

public interface FlightService {

	public ResponseEntity<String> createflight(FlightDTO flightdto);
	public ResponseEntity<String> updateflight(String flightid,FlightDTO flightdto);
	public ResponseEntity<String> deleteflight(String flightid);
	public Optional<Flight> findflightById(String flightid);
	public List<Flight> findAllflight();
	public List<Flight> findFlightBySourceAndDesitnation(String source, String desination);
	
}
