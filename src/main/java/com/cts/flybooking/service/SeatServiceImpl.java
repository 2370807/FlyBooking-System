package com.cts.flybooking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.flybooking.dto.DispSeatDTO;
import com.cts.flybooking.dto.SeatDTO;
import com.cts.flybooking.model.Booking;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.model.Price;
import com.cts.flybooking.model.Seat;
import com.cts.flybooking.repository.BookingRepository;
import com.cts.flybooking.repository.FlightRepository;
import com.cts.flybooking.repository.PriceRepository;
import com.cts.flybooking.repository.SeatRepository;


@Service
public class SeatServiceImpl implements SeatService  {

	private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);
	
	@Autowired 
	private SeatRepository seatRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public ResponseEntity<String> createSeat(SeatDTO seatDTO) {
		// TODO Auto-generated method stub
		logger.info("Creating seat for flight number: {}, seat class: {}", seatDTO.getFightnumber(),seatDTO.getSeatclass());
		 Flight flight1=flightRepository.findByFlightnumber(seatDTO.getFightnumber())
				 .orElseThrow(() -> new RuntimeException("Flight not found"));
		 Price price=priceRepository.findByClassname(seatDTO.getSeatclass())
				 .orElseThrow(()->new RuntimeException("Invalid SeatClass"));
		 Seat seat=seatRepository.findByFlight_FlightnumberAndSeatnumberAndPrice_Classname(seatDTO.getFightnumber(),(Long.valueOf(seatDTO.getSeatnumber())),seatDTO.getSeatclass());
		 if(seat==null) 
		 { 
			 Seat seat1=new Seat();
			 seat1.setFlight(flight1); 
			 seat1.setSeatnumber((Long.valueOf(seatDTO.getSeatnumber()))); 
			 seat1.setIsavailable(seatDTO.isIsavailable());
			 seat1.setPrice(price);
			 flight1.getSeats().add(seat1);
			 flight1.setSeats(flight1.getSeats());
			 seatRepository.save(seat1);
			 logger.info("Seat added successfully for flight number: {}", seatDTO.getFightnumber());
			 return ResponseEntity.status(HttpStatus.OK).body("Seat added successfully");
		}
		else
		{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This seatnumber "+(Long.valueOf(seatDTO.getSeatnumber()))+" already exist in "+seatDTO.getFightnumber()+" flight");
		}
	}

	@Override
	public List<DispSeatDTO> getAvailableSeats(String flightnumber) { //need to check
		// TODO Auto-generated method stub
		logger.info("Fetching available seats for flight number: {}", flightnumber);
		List<Seat> seats=seatRepository.findByFlight_FlightnumberAndIsavailable(flightnumber, true);
		return seats.stream().map(this::convertToDispSeatDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ResponseEntity<String> removeSeat(long seatnumber,String Seatclass) {
		// TODO Auto-generated method stub
		logger.info("Removing seat with seat number: {}", seatnumber);
		seatRepository.deleteBySeatnumberAndPrice_Classname(seatnumber,Seatclass);
		logger.info("Seat removed successfully with seat number: {}", seatnumber);
		return ResponseEntity.status(HttpStatus.OK).body("Seat removed");
	}
	
	@Override
	public List<DispSeatDTO> findAll(String flightnumber) {
		// TODO Auto-generated method stub
		logger.info("Fetching all seats for flight number: {}", flightnumber);
		List<Seat> seats=seatRepository.findByFlight_Flightnumber(flightnumber);
		return seats.stream().map(this::convertToDispSeatDTO).collect(Collectors.toList());	
	}
	
	public DispSeatDTO convertToDispSeatDTO(Seat seat)
	{ 
		DispSeatDTO dispSeatDTO =new DispSeatDTO();
		dispSeatDTO.setSeatnumber(seat.getSeatnumber());
		dispSeatDTO.setIsavailable(seat.isIsavailable());
		dispSeatDTO.setPrice(seat.getPrice().getPrice()); 
		dispSeatDTO.setSeatnumber(seat.getSeatnumber()); 
		dispSeatDTO.setSeatId(seat.getId()); 
		dispSeatDTO.setClassname(seat.getPrice().getClassname());
		logger.debug("Converted Seat to DispSeatDTO: {}", dispSeatDTO);
		return dispSeatDTO; 
	}

	@Override
	public Seat updateseat(String flightnumber,SeatDTO seatDTO) {
		// TODO Auto-generated method stub
		Price price=priceRepository.findByClassname(seatDTO.getSeatclass()).orElseThrow(()->new RuntimeException("SeatClass not found"));
		// Flight flight=flightRepository.findByFlightnumber(seatDTO.getFightnumber()).orElseThrow(()->new RuntimeException("Flight not found"));
		Seat seat=seatRepository.findByFlight_FlightnumberAndSeatnumberAndPrice_Classname(seatDTO.getFightnumber(),Long.valueOf(seatDTO.getSeatnumber()),seatDTO.getSeatclass());
		seat.setSeatnumber(Long.valueOf(seatDTO.getSeatnumber()));
		if(seatDTO.isIsavailable()==true)
		{
			if(seat.isIsavailable()==false)
			{
				List<Booking> bookings = bookingRepository.findByFlight_FlightnumberAndSeat_SeatnumberAndSeat_Price_Classname(seatDTO.getFightnumber(),seat.getSeatnumber(),seatDTO.getSeatclass()); // Fetch booking from the database
				for(Booking booking:bookings) {
					booking.setStatus("Cancelled by Company");
				}
			}
		}
		seat.setIsavailable(seatDTO.isIsavailable());
		seat.setPrice(price);
		return 	seatRepository.save(seat);
	}
	
}
