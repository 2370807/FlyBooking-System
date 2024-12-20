package com.cts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.dto.BookingDTO;
import com.cts.model.Booking;

public interface BookingService {
	    public ResponseEntity<String> createBooking(long userId, String flightnumber, long seatId);  
	    public List<BookingDTO> getBookingsByUser(long userId); 
	    public BookingDTO convertToBookingDTO(Booking booking);
	    public void cancelBooking(long bookingId);
	    public Booking updateBooking(long bookingId, BookingDTO bookingDTO);
	    //public long totalprice(int no_of_seat,long price);
}
