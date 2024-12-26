package com.cts.flybooking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService {

	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Override
	public ResponseEntity<String> createflight(FlightDTO flightdto) {
		// TODO Auto-generated method stub
		logger.info("Creating flight with flight number: {}", flightdto.getFlightnumber());
		Flight flight=Flight.builder()
				.flightnumber(flightdto.getFlightnumber())
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.desination(flightdto.getDesitination())
				.source(flightdto.getSource())
				.build();
		flightRepository.save(flight);
		logger.info("Flight with flight number: {} added to list successfully", flightdto.getFlightnumber());
		return ResponseEntity.status(HttpStatus.OK).body("Flight added to list Successfully");
	}

	@Override
	public ResponseEntity<String> updateflight(String flightid, FlightDTO flightdto) {
		// TODO Auto-generated method stub
		logger.info("Updating flight with flight id: {}", flightid);
		Flight flight=Flight.builder()
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.desination(flightdto.getDesitination())
				.source(flightdto.getSource())
				.flightnumber(flightdto.getFlightnumber())//--changed
				.build();
		flightRepository.save(flight);
		logger.info("Updated flight with flight id: {} successfully", flightid);
		return ResponseEntity.status(HttpStatus.OK).body("Updated value is saved successfully!");
	}

	@Override
	public ResponseEntity<String> deleteflight(String flightnumber) {
		// TODO Auto-generated method stub
		logger.info("Deleting flight with flight number: {}", flightnumber);
		flightRepository.deleteById(flightnumber);
		logger.info("Deleted flight with flight number: {}",flightnumber);
		return ResponseEntity.status(HttpStatus.OK).body("This "+flightnumber + " details has been removed");
	}

	@Override
	public Optional<Flight> findflightById(String flightid) {
		// TODO Auto-generated method stub
		logger.info("Finding flight with flight id: {}", flightid);
		return flightRepository.findByFlightnumber(flightid);
	}

	@Override
	public List<Flight> findAllflight() {
		// TODO Auto-generated method stub
		logger.info("Finding all flights");		
		return flightRepository.findAll();
	}
	@Override
	public List<Flight> findFlightBySourceAndDesitnation(String source,String desination)
	{
		logger.info("Finding flight from source: {} to destination: {}", source, desination);
		return flightRepository.findBySourceAndDesination(source, desination);
	}
}
