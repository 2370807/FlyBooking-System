package com.cts.flybooking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.BookingDTO;
import com.cts.flybooking.dto.InitiateBookingDTO;
import com.cts.flybooking.model.Booking;

public interface BookingService {
	    public ResponseEntity<String> createBooking(InitiateBookingDTO initiateBookingDTO);  
	    public List<BookingDTO> getBookingsByUser(long userId); 
	    public BookingDTO convertToBookingDTO(Booking booking);
	    public void cancelBooking(long bookingId);
	    public Booking updateBooking(long bookingId, BookingDTO bookingDTO);
	    //public long totalprice(int no_of_seat,long price);
}
