package com.cts.flybooking.dto;

import java.time.LocalDate;

import com.cts.flybooking.model.Flight;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
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
	
	@FutureOrPresent(message="Booking Date cannot be in past")
	private LocalDate BookingDate;
	
	@NotNull(message="Seatclass must have value")
	private String Seatclass;
	
}
