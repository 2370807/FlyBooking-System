package com.cts.flybooking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDTO {
	
	private long bookingId;
	
	private String username; 
	
//	private String firstName; 
	 
//	private String lastName; 
	
	private String flightNumber; 
	
	private String airline; 
	
	private String source; 
	
	private String destination; 
	
	private long seatNumber; 
	
	private LocalDate bookingDate; 
	
	private String seatClass;
	
	private long price;
	
	private String status;
	
	private int no_of_seats;
}
