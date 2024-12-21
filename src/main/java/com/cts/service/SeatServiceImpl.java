package com.cts.service;

import java.util.List;
import java.util.Optional;

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

	@Autowired 
	private SeatRepository seatRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	
	@Override
	public ResponseEntity<String> createSeat(SeatDTO seatDTO) {
		// TODO Auto-generated method stub
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
		 return ResponseEntity.status(HttpStatus.OK).body("Seat added successfully");
	}

	@Override
	public List<Seat> getAvailableSeats(String flightnumber) { //need to check
		// TODO Auto-generated method stub
		return seatRepository.findByFlight_FlightnumberAndIsavailable(flightnumber, true);
	}

	@Override
	@Transactional
	public ResponseEntity<String> removeSeat(long seatnumber) {
		// TODO Auto-generated method stub
		seatRepository.deleteBySeatnumber(seatnumber);
		return ResponseEntity.status(HttpStatus.OK).body("Seat removed");
	}
	@Override
	public List<Seat> findAll(String flightnumber) {
		// TODO Auto-generated method stub
		return seatRepository.findByFlight_Flightnumber(flightnumber);
	}
	
	
}
