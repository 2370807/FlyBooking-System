package com.cts.flybooking.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.flybooking.dto.SeatDTO;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.model.Price;
import com.cts.flybooking.model.Seat;
import com.cts.flybooking.repository.FlightRepository;
import com.cts.flybooking.repository.PriceRepository;
import com.cts.flybooking.repository.SeatRepository;
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
		 Seat seat=seatRepository.findByFlight_FlightnumberAndSeatnumberAndPrices_Classname(seatDTO.getFightnumber(),(Long.valueOf(seatDTO.getSeatnumber())),seatDTO.getSeatclass());
		 if(seat==null) 
		 { 
			 Seat seat1=new Seat();
			 seat1.setFlight(flight1); 
			 seat1.setSeatnumber((Long.valueOf(seatDTO.getSeatnumber()))); 
			 seat1.setIsavailable(seatDTO.isIsavailable());
			 seat1.setPrices(price);
			 flight1.getSeats().add(seat1);
			 flight1.setSeats(flight1.getSeats());
			 seatRepository.save(seat1);
			 logger.info("Seat added successfully for flight number: {}", seatDTO.getFightnumber());
			 return ResponseEntity.status(HttpStatus.OK).body("Seat added successfully");
		}
		else
		{
			 return ResponseEntity.status(HttpStatus.OK).body("This seatnumber "+(Long.valueOf(seatDTO.getSeatnumber()))+" already exist in "+seatDTO.getFightnumber()+" flight");
		}
	}

	@Override
	public List<Seat> getAvailableSeats(String flightnumber) { //need to check
		// TODO Auto-generated method stub
		logger.info("Fetching available seats for flight number: {}", flightnumber);
		return seatRepository.findByFlight_FlightnumberAndIsavailable(flightnumber, true);
	}

	@Override
	@Transactional
	public ResponseEntity<String> removeSeat(long seatnumber,String Seatclass) {
		// TODO Auto-generated method stub
		logger.info("Removing seat with seat number: {}", seatnumber);
		seatRepository.deleteBySeatnumberAndPrices_Classname(seatnumber,Seatclass);
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
