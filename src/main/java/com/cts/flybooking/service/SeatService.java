package com.cts.flybooking.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.SeatDTO;
import com.cts.flybooking.model.Seat;

public interface SeatService {
	
	public ResponseEntity<String> createSeat(SeatDTO seatDTO);
	public List<Seat> getAvailableSeats(String flightnumber);
	public ResponseEntity<String> removeSeat(long seatnumber,String seatclass);
	//public List<Seat> findAll();
	public List<Seat> findAll(String flightnumber);
}
