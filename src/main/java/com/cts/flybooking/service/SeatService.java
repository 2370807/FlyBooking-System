package com.cts.flybooking.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.DispSeatDTO;
import com.cts.flybooking.dto.SeatDTO;
import com.cts.flybooking.model.Seat;

public interface SeatService {
	
	public ResponseEntity<String> createSeat(SeatDTO seatDTO);
	public List<DispSeatDTO> getAvailableSeats(String flightnumber);
	public ResponseEntity<String> removeSeat(long seatnumber,String seatclass);
	//public List<Seat> findAll();
	public List<DispSeatDTO> findAll(String flightnumber);
	public Seat updateseat(String flightnumber,SeatDTO seatDTO);
}
