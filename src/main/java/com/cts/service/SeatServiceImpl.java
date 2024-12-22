package com.cts.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.dto.SeatDTO;
import com.cts.model.Flight;
import com.cts.model.Price;
import com.cts.model.Seat;
import com.cts.repository.FlightRepository;
import com.cts.repository.PriceRepository;
import com.cts.repository.SeatRepository;
@Service
public class SeatServiceImpl implements SeatService  {

	private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);
	
	@Autowired 
	private SeatRepository seatRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	
	@Override
	public ResponseEntity<String> createSeat(SeatDTO seatDTO) {
		// TODO Auto-generated method stub
		logger.info("Creating seat for flight number: {}, seat class: {}", seatDTO.getFightnumber(),seatDTO.getSeatclass());
		 Flight flight1=flightRepository.findByFlightnumber(seatDTO.getFightnumber())
				 .orElseThrow(() -> new RuntimeException("Flight not found"));
		 Price price=priceRepository.findByClassname(seatDTO.getSeatclass())
				 .orElseThrow(()->new RuntimeException("Invalid SeatClass"));
		 Seat seat=new Seat();
		 seat.setFlight(flight1); 
		 //seat.setSeatClass(seatClass1);
		 seat.setSeatnumber((Long.valueOf(seatDTO.getSeatnumber()))); 
		 seat.setIsavailable(seatDTO.isIsavailable());
		 seat.setPrices(price);
		 flight1.getSeats().add(seat);
		 flight1.setSeats(flight1.getSeats());
		 seatRepository.save(seat);
		 logger.info("Seat added successfully for flight number: {}", seatDTO.getFightnumber());
		 return ResponseEntity.status(HttpStatus.OK).body("Seat added successfully");
	}

	@Override
	public List<Seat> getAvailableSeats(String flightnumber) { //need to check
		// TODO Auto-generated method stub
		logger.info("Fetching available seats for flight number: {}", flightnumber);
		return seatRepository.findByFlight_FlightnumberAndIsavailable(flightnumber, true);
	}

	@Override
	@Transactional
	public ResponseEntity<String> removeSeat(long seatnumber) {
		// TODO Auto-generated method stub
		logger.info("Removing seat with seat number: {}", seatnumber);
		seatRepository.deleteBySeatnumber(seatnumber);
		logger.info("Seat removed successfully with seat number: {}", seatnumber);
		return ResponseEntity.status(HttpStatus.OK).body("Seat removed");
	}
	@Override
	public List<Seat> findAll(String flightnumber) {
		// TODO Auto-generated method stub
		logger.info("Fetching all seats for flight number: {}", flightnumber);
		return seatRepository.findByFlight_Flightnumber(flightnumber);
	}
}
