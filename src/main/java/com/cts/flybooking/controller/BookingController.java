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
import com.cts.flybooking.dto.InitiateBookingDTO;
import com.cts.flybooking.model.Booking;
import com.cts.flybooking.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Booking")
public class BookingController {

	@Autowired
	private BookingService bookingService; 
	
	@PostMapping("/intiatebooking")//--give booking dto
	public ResponseEntity<String> createBooking(@RequestBody @Valid InitiateBookingDTO initiateBookingdto) 
	{ 
		return bookingService.createBooking(initiateBookingdto); 
		//return ResponseEntity.status(HttpStatus.OK).body(bookingService.createBooking(userId, flightnumber, seatId));
	} 
	
	@GetMapping("/BookingByUser/{userId}") 
	public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable long userId) 
	{ 
		List<BookingDTO> bookings = bookingService.getBookingsByUser(userId); 
		return ResponseEntity.ok(bookings);
	} 
	
	@DeleteMapping("/cancel/{bookingId}") //--check it//check the canceling logic well we could able to apply the same seat 2 times.
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
	
	@DeleteMapping("/cancelByCompany/{BookingId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> cancelBookingByCompany(@PathVariable long BookingId)
	{
		bookingService.cancelBookingByCompany(BookingId);
		return ResponseEntity.status(HttpStatus.OK).body("Booking has been canceled by Company");
	}
	
	@GetMapping("/BookingByFlight/{flightNumber}")
	public List<BookingDTO> getBookingsByFlight(@PathVariable String flightNumber) {
		//System.out.println("Fetching bookings for flight: " + flightNumber);
	    return bookingService.getBookingsByFlight(flightNumber);
	}
}
