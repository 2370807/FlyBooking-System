package com.cts.flybooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flybooking.dto.DispSeatDTO;
import com.cts.flybooking.dto.SeatDTO;
import com.cts.flybooking.model.Seat;
import com.cts.flybooking.service.SeatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seat")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@PostMapping("/newseat")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> addseat(@RequestBody @Valid SeatDTO seatDTO)
	{
		return seatService.createSeat(seatDTO);
	}
	
	@GetMapping("/availableseat/{flightnumber}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<DispSeatDTO> availableSeat(@PathVariable String flightnumber)
	{
		return seatService.getAvailableSeats(flightnumber);
	}
	
	@DeleteMapping("/deleteseat/{seatnumber}/{seatclass}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteSeat(@PathVariable long seatnumber,@PathVariable String seatclass)
	{ 
		seatService.removeSeat(seatnumber,seatclass);
	}
	
	@GetMapping("/allseatsofthisflight/{flightnumber}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<DispSeatDTO> getAllSeats(@PathVariable String flightnumber)
	{
		return seatService.findAll(flightnumber);
	}
	
	@PutMapping("/updateseat/{flightnumber}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Seat updateseat(@PathVariable String flightnumber,@RequestBody @Valid SeatDTO seatDTO) {
		return seatService.updateseat(flightnumber,seatDTO);
	}

}
