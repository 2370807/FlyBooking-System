package com.cts.flybooking.controller;

import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flybooking.dto.BookingDTO;
import com.cts.flybooking.service.BookingService;

@RestController
@RequestMapping("/Booking")
public class BookingController {

	@Autowired
	private BookingService bookingService; 
	
	@PostMapping("/intiatebooking/{userId}/{flightnumber}/{seatId}/{no_of_seat}/{BookingDate}")//--give booking dto
	//@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> createBooking(@PathVariable long userId, @PathVariable String flightnumber, @PathVariable long seatId,@PathVariable int no_of_seat,@PathVariable LocalDate BookingDate) 
	{ 
		return bookingService.createBooking(userId, flightnumber, seatId,no_of_seat,BookingDate); 
		//return ResponseEntity.status(HttpStatus.OK).body(bookingService.createBooking(userId, flightnumber, seatId));
	} 
	
	@GetMapping("/BookingByUser/{userId}") //--check it
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable long userId) 
	{ 
		List<BookingDTO> bookings = bookingService.getBookingsByUser(userId); 
		return ResponseEntity.ok(bookings);
	} 
	
	@DeleteMapping("/cancel/{bookingId}") //--check it//check the cancelling logic well we could able to apply the same seat 2 times.
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> cancelBooking(@PathVariable long bookingId) 
	{ 
		bookingService.cancelBooking(bookingId); 
		return ResponseEntity.status(HttpStatus.OK).body("Booking Cancelled"); 
	} 
	
	@PutMapping("/UpdateBooking/{bookingId}") //--check it
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateBooking(@PathVariable long bookingId, @RequestBody BookingDTO bookingDTO) 
	{ 
		bookingService.updateBooking(bookingId, bookingDTO); 
		return ResponseEntity.status(HttpStatus.OK).body("Booking updated");
	}
	
	
}
