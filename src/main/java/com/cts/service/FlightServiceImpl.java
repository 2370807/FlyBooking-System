package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.dto.FlightDTO;
import com.cts.model.Flight;
import com.cts.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;
	
	@Override
	public ResponseEntity<String> createflight(FlightDTO flightdto) {
		// TODO Auto-generated method stub
		Flight flight=Flight.builder()
				.flightnumber(flightdto.getFlightnumber())
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.desination(flightdto.getDesitination())
				.source(flightdto.getSource())
				.build();
		flightRepository.save(flight);
		return ResponseEntity.status(HttpStatus.OK).body("Flight added to list Successfully");
	}

	@Override
	public ResponseEntity<String> updateflight(String flightid, FlightDTO flightdto) {
		// TODO Auto-generated method stub
		Flight flight=Flight.builder()
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.desination(flightdto.getDesitination())
				.source(flightdto.getSource())
				.flightnumber(flightdto.getFlightnumber())//--changed
				.build();
		flightRepository.save(flight);
		return ResponseEntity.status(HttpStatus.OK).body("Updated value is saved successfully!");
	}

	@Override
	public ResponseEntity<String> deleteflight(String flightnumber) {
		// TODO Auto-generated method stub
		flightRepository.deleteById(flightnumber);
		return ResponseEntity.status(HttpStatus.OK).body("This "+flightnumber + " details has been removed");
	}

	@Override
	public Optional<Flight> findflightById(String flightid) {
		// TODO Auto-generated method stub
		return flightRepository.findByFlightnumber(flightid);
	}

	@Override
	public List<Flight> findAllflight() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}
	@Override
	public Flight findFlightBySourceAndDesitnation(String source,String desination)
	{
		return flightRepository.findBySourceAndDesination(source, desination);
	}
	

}
