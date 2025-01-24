package com.cts.flybooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;

public interface FlightService {

	public ResponseEntity<String> createflight(FlightDTO flightdto);
	public ResponseEntity<String> updateflight(String flightid,FlightDTO flightdto);
	public ResponseEntity<String> deleteflight(String flightid);
	public Optional<Flight> findflightById(String flightid);
	public List<Flight> findAllflight();
	public List<Flight> findFlightBySourceAndDesitnation(String source, String desination);
	public ResponseEntity<List<String>> getAllFlightFromSource();
	public ResponseEntity<List<String>> getAllFlightFromDestinations();
	public List<Flight> findFlightsBySourceDestinationAndDate(String source, String destination,LocalDate departureDate);
	public List<Flight> findFutureFlights();
	
}
