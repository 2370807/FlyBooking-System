package com.cts.controller;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BookingDTO;
import com.cts.model.Booking;
import com.cts.service.BookingService;

@RestController
@RequestMapping("/Booking")
public class BookingController {

	@Autowired
	private BookingService bookingService; 
	
	@PostMapping("/intiatebooking/{userId}/{flightnumber}/{seatId}")//--give booking dto
	public ResponseEntity<String> createBooking(@PathVariable long userId, @PathVariable String flightnumber, @PathVariable long seatId) 
	{ 
		return bookingService.createBooking(userId, flightnumber, seatId); 
		//return ResponseEntity.status(HttpStatus.OK).body(bookingService.createBooking(userId, flightnumber, seatId));
	} 
	
	@GetMapping("/BookingByUser/{userId}") //--check it
	public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable long userId) 
	{ 
		List<BookingDTO> bookings = bookingService.getBookingsByUser(userId); 
		return ResponseEntity.ok(bookings);
	} 
	
	@DeleteMapping("/cancel/{bookingId}") //--check it//check the cancelling logic well we could able to apply the same seat 2 times.
	public ResponseEntity<String> cancelBooking(@PathVariable long bookingId) 
	{ 
		bookingService.cancelBooking(bookingId); 
		return ResponseEntity.status(HttpStatus.OK).body("Booking Cancelled"); 
	} 
	
	@PutMapping("/UpdateBooking/{bookingId}") //--check it
	public ResponseEntity<String> updateBooking(@PathVariable long bookingId, @RequestBody BookingDTO bookingDTO) 
	{ 
		bookingService.updateBooking(bookingId, bookingDTO); 
		return ResponseEntity.status(HttpStatus.OK).body("Booking updated");
	}
	
	
}
