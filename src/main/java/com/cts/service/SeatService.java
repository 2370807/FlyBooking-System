package com.cts.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.dto.SeatDTO;
import com.cts.model.Seat;

public interface SeatService {
	
	public ResponseEntity<String> createSeat(SeatDTO seatDTO);
	public List<Seat> getAvailableSeats(String flightnumber);
	public ResponseEntity<String> removeSeat(long seatnumber);
	//public List<Seat> findAll();
	public List<Seat> findAll(String flightnumber);
}
