package com.cts.flybooking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitiateBookingDTO {
	
	@NotNull(message="UserId cannot be null")
	private long userId;
	
	@NotNull(message="Flightnumber cannot be null")
	private String flightnumber;
	
	@NotNull(message="Seatnumber cannot be null")
	private long seatnumber;
	
	@Min(value=1,message="Minimum number of seats cannot be 0")
	private int no_of_seats;
	
	@Future(message="Booking Date cannot be in present or in past")
	private LocalDate BookingDate;
}