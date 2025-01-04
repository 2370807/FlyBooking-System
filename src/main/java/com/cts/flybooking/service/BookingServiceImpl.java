package com.cts.flybooking.service;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.flybooking.dto.BookingDTO;
import com.cts.flybooking.dto.InitiateBookingDTO;
import com.cts.flybooking.model.Booking;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.model.Passenger;
import com.cts.flybooking.model.Seat;
import com.cts.flybooking.repository.BookingRepository;
import com.cts.flybooking.repository.FlightRepository;
import com.cts.flybooking.repository.PassengerRepository;
import com.cts.flybooking.repository.SeatRepository;

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
	public ResponseEntity<String> createBooking(InitiateBookingDTO initiateBookingDTO)
	{ 
		logger.info("Creating booking for userId: {}, flightnumber: {}, seatId: {}, no_of_seats: {}, BookingDate: {}", 
				initiateBookingDTO.getUserId(), initiateBookingDTO.getFlightnumber(), initiateBookingDTO.getSeatnumber(),1,initiateBookingDTO.getBookingDate());
		Passenger user = userRepository.findById(initiateBookingDTO.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found")); 
		Flight flight = flightRepository.findByFlightnumber(initiateBookingDTO.getFlightnumber())
				.orElseThrow(() -> new RuntimeException("Flight not found")); 
		Seat seat = seatRepository.findByFlight_FlightnumberAndSeatnumberAndPrice_Classname(initiateBookingDTO.getFlightnumber(),initiateBookingDTO.getSeatnumber(),initiateBookingDTO.getSeatclass()); 
		Booking booking = new Booking(); 
		booking.setUser(user);
		booking.setFlight(flight); 
		if(seat.isIsavailable()==true)
		{
			booking.setSeat(seat);
			seat.setIsavailable(false);
			booking.setBookingDate(initiateBookingDTO.getBookingDate());
			System.out.println(booking.getBookingDate());
			System.out.println(initiateBookingDTO.getBookingDate());
			booking.setNo_of_seats(1);
			booking.setStatus("CONFIRMED"); 
			bookingRepository.save(booking);
			logger.info("Booking successfully made for userId: {}, flightnumber: {}, seatId: {}", 
					initiateBookingDTO.getUserId(), initiateBookingDTO.getFlightnumber(), initiateBookingDTO.getSeatnumber());
			return ResponseEntity.status(HttpStatus.OK).body("Booking is successfully made!");
		}
		else
		{
			logger.warn("Seat already booked for seatId: {}", initiateBookingDTO.getSeatnumber());
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
		bookingDTO.setPrice(totalprice(booking.getNo_of_seats(), booking.getSeat().getPrice().getPrice()));
		bookingDTO.setSeatClass(booking.getSeat().getPrice().getClassname());
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
			Seat seat = seatRepository.findBySeatnumber(bookingDTO.getSeatNumber()) 
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
	
	@Override
	public void cancelBookingByCompany(String flightnumber)
	{ 
		List<Booking> bookings = bookingRepository.findByFlight_Flightnumber(flightnumber); 
		if (bookings.isEmpty()) 
		{ 
			throw new RuntimeException("No bookings found for this flight"); 
		} 
		for (Booking booking : bookings) 
		{ 
			booking.setStatus("Cancelled by Company");
			bookingRepository.save(booking);
		}
	}
	
}
