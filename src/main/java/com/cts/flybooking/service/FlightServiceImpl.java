package com.cts.flybooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Booking;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.repository.BookingRepository;
import com.cts.flybooking.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService {

	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
//	@Autowired
//	private BookingService bookingService;
	
	@Override
	public ResponseEntity<String> createflight(FlightDTO flightdto) {
		// TODO Auto-generated method stub
		logger.info("Creating flight with flight number: {}", flightdto.getFlightnumber());
		if(flightRepository.findByFlightnumber(flightdto.getFlightnumber()).isEmpty()) {
		Flight flight=Flight.builder()
				.flightnumber(flightdto.getFlightnumber())
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.destination(flightdto.getDestination())
				.source(flightdto.getSource())
				.build();
		flightRepository.save(flight);
		logger.info("Flight with flight number: {} added to list successfully", flightdto.getFlightnumber());
		return ResponseEntity.status(HttpStatus.OK).body("Flight added to list Successfully");}
		else
		{
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight already exist");
		}
	}

	@Override
	public ResponseEntity<String> updateflight(String flightnumber, FlightDTO flightdto) {
		// TODO Auto-generated method stub
		logger.info("Updating flight with flight id: {}", flightnumber);
		Flight flight = flightRepository.findByFlightnumber(flightnumber)
				.orElseThrow(()->new RuntimeException("Flight with "+flightnumber+" not found"));
		flight=Flight.builder()
				.airline(flightdto.getAirline())
				.arrival_time(flightdto.getArrival_time())
				.departure_time(flightdto.getDeparture_time())
				.destination(flightdto.getDestination())
				.source(flightdto.getSource())
				.flightnumber(flightdto.getFlightnumber())//--changed
				.build();
		flightRepository.save(flight);
		logger.info("Updated flight with flight id: {} successfully", flightnumber);
		return ResponseEntity.status(HttpStatus.OK).body("Updated value is saved successfully!");
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteflight(String flightnumber) {
		// TODO Auto-generated method stub
		logger.info("Deleting flight with flight number: {}", flightnumber);
		Flight flight=flightRepository.findByFlightnumber(flightnumber)
				.orElseThrow(()->new RuntimeException("The "+flightnumber+" is not present"));	
//		bookingService.cancelBookingByCompany(flightnumber);
//		bookingRepository.deleteByFlight(flightnumber);
		List<Booking> bookings = bookingRepository.findByFlight_Flightnumber(flightnumber);
		if (!bookings.isEmpty()) 
		{ 
			for (Booking booking : bookings) 
			{ 
				booking.setStatus("Cancelled by Company"); 
				bookingRepository.save(booking);  
				 
			}
		}
		flightRepository.delete(flight);
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
	    return flightRepository.findAll();
	}
	@Override
	public List<Flight> findFlightBySourceAndDesitnation(String source,String desination)
	{
		logger.info("Finding flight from source: {} to destination: {}", source, desination);
		return flightRepository.findBySourceAndDestination(source, desination);
	}

	@Override
	public ResponseEntity<List<String>> getAllFlightFromSource() {
		// TODO Auto-generated method stub List<String> sources = flightRepository.findDistinctSources();
		 List<String> sources = flightRepository.findDistinctSource();
	     return ResponseEntity.ok(sources); 
	}

	@Override
	public ResponseEntity<List<String>> getAllFlightFromDestinations() {
		// TODO Auto-generated method stub
		List<String> destinations = flightRepository.findDistinctDestination();
        return ResponseEntity.ok(destinations);
	}
	
	@Override
	public List<Flight> findFlightsBySourceDestinationAndDate(String source, String destination, LocalDate departureDate) {
	        return flightRepository.findBySourceAndDestinationAndDepartureTime(source, destination, departureDate);
	    }

	@Override
	public List<Flight> findFutureFlights() {
		// TODO Auto-generated method stub
		 LocalDateTime now = LocalDateTime.now();
	     return flightRepository.findByDepartureTimeGreaterThanEqual(now);
	}
}
