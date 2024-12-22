package com.cts.service;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.dto.BookingDTO;
import com.cts.model.Booking;
import com.cts.model.Flight;
import com.cts.model.Seat;
import com.cts.model.Passenger;
import com.cts.repository.BookingRepository;
import com.cts.repository.FlightRepository;
import com.cts.repository.SeatRepository;
import com.cts.repository.PassengerRepository;

@Service
public class BookingServiceImpl implements BookingService{

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@Autowired 
	private BookingRepository bookingRepository;
	
	@Autowired 
	private PassengerRepository userRepository;
	
	@Autowired 
	private FlightRepository flightRepository;
	
	@Autowired 
	private SeatRepository seatRepository;
	
	@Override
	public ResponseEntity<String> createBooking(long userId, String flightnumber, long seatId ,int no_of_seats,LocalDate BookingDate)
	{ 
		logger.info("Creating booking for userId: {}, flightnumber: {}, seatId: {}, no_of_seats: {}, BookingDate: {}", 
				userId, flightnumber, seatId, no_of_seats,BookingDate);
		Passenger user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found")); 
		Flight flight = flightRepository.findByFlightnumber(flightnumber)
				.orElseThrow(() -> new RuntimeException("Flight not found")); 
		Seat seat = seatRepository.findById(seatId)
				.orElseThrow(() -> new RuntimeException("Seat not found")); 
		Booking booking = new Booking(); 
		booking.setUser(user);
		booking.setFlight(flight); 
		if(seat.isIsavailable()==true)
		{
			booking.setSeat(seat);
			seat.setIsavailable(false);
			booking.setBookingDate(BookingDate);
			booking.setNo_of_seats(no_of_seats);
			booking.setStatus("CONFIRMED"); 
			bookingRepository.save(booking);
			logger.info("Booking successfully made for userId: {}, flightnumber: {}, seatId: {}", 
					userId, flightnumber,seatId);
			return ResponseEntity.status(HttpStatus.OK).body("Booking is successfully made!");
		}
		else
		{
			logger.warn("Seat already booked for seatId: {}", seatId);
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Seat Already Booked");
		}
	}
	
	@Override
	public List<BookingDTO> getBookingsByUser(long userId)
	{ 
		logger.info("Fetching bookings for userId: {}", userId); 
		List<Booking> bookings = bookingRepository.findByUser_Id(userId); 
		return bookings.stream().map(this::convertToBookingDTO).collect(Collectors.toList()); 
	}
	
	@Override
	public BookingDTO convertToBookingDTO(Booking booking)
	{ 
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setUsername(booking.getUser().getName()); 
		bookingDTO.setFlightNumber(booking.getFlight().getFlightnumber()); 
		bookingDTO.setAirline(booking.getFlight().getAirline()); 
		bookingDTO.setSource(booking.getFlight().getSource()); 
		bookingDTO.setDestination(booking.getFlight().getDesination()); 
		bookingDTO.setSeatNumber(booking.getSeat().getSeatnumber()); 
		bookingDTO.setBookingDate(booking.getBookingDate()); 
		bookingDTO.setStatus(booking.getStatus()); 
		bookingDTO.setPrice(totalprice(booking.getNo_of_seats(), booking.getSeat().getPrices().getPrice()));
		bookingDTO.setSeatClass(booking.getSeat().getPrices().getClassname());
		bookingDTO.setNo_of_seats(booking.getNo_of_seats());
		logger.debug("Converted Booking to BookingDTO: {}", bookingDTO);
		return bookingDTO; 
	}
	
	@Override
	public void cancelBooking(long bookingId) 
	{ 
		logger.info("Cancelling booking with bookingId: {}",bookingId);
		Booking booking = bookingRepository.findById(bookingId) 
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus("CANCELED"); 
		bookingRepository.save(booking); 

		Seat seat = booking.getSeat(); 
		seat.setIsavailable(true);
		seatRepository.save(seat);
		logger.info("Booking cancelled and seat availability updated for bookingId: {}", bookingId); 

	}
	
	@Override
	public Booking updateBooking(long bookingId, BookingDTO bookingDTO)
	{ 
		logger.info("Updating booking with bookingId: {}", bookingId);
		Booking booking = bookingRepository.findById(bookingId) 
				.orElseThrow(() -> new RuntimeException("Booking not found")); 
		if (bookingDTO.getFlightNumber() != null) 
		{ 
			Flight flight = flightRepository.findById(bookingDTO.getFlightNumber()) 
					.orElseThrow(() -> new RuntimeException("Flight not found")); 
			booking.setFlight(flight); 
		} 
		if (bookingDTO.getSeatNumber() != 0.0) 
		{ 
			Seat seat = seatRepository.findById(bookingDTO.getSeatNumber()) 
					.orElseThrow(() -> new RuntimeException("Seat not found")); 
			booking.setSeat(seat); 
		} 
		booking.setStatus(bookingDTO.getStatus() != null ? bookingDTO.getStatus() : booking.getStatus()); 
		logger.info("Booking updated for bookingId: {}", bookingId);
		return bookingRepository.save(booking);
	}
	
	public long totalprice(int no_of_seat,double price)
	{
		logger.debug("Calculated total price: {}", (no_of_seat*price));
		return (long) (no_of_seat*price);
	}
	
}
