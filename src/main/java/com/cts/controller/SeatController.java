package com.cts.controller;

import java.util.List;
import com.cts.dto.SeatDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Seat;
import com.cts.service.SeatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seat")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@PostMapping("/newseat")
	public ResponseEntity<String> addseat(@RequestBody @Valid SeatDTO seatDTO)
	{
		return seatService.createSeat(seatDTO);
	}
	
	@GetMapping("/availableseat/{flightnumber}")
	public List<Seat> availableSeat(@PathVariable String flightnumber)
	{
		return seatService.getAvailableSeats(flightnumber);
	}
	
	@DeleteMapping("/deleteseat/{seatnumber}")
	public void deleteSeat(@PathVariable long seatnumber)
	{ 
		seatService.removeSeat(seatnumber);
	}
}
