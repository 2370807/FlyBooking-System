package com.cts.flybooking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightDTO {

	@NotNull(message="Enter a valid flightnumber")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "The field must be alphanumeric")
	private String flightnumber;
	
	@NotNull(message="Enter a valid airline")
	private String airline;
	
	@NotBlank(message="Source cannot be blank")
	private String source;
	
	@NotBlank(message="Destination cannot be blank")
	private String destination;
	
	//@NotBlank(message="Departure time cannot be empty")
	@FutureOrPresent(message = "The start time must be in the future")
	@NotNull
	private LocalDateTime departure_time;
	
	//@NotBlank(message="Arrival time cannot be empty")
	@FutureOrPresent(message = "The end time must be in the future")
	@NotNull
	private LocalDateTime arrival_time;

		// TODO Auto-generated method stub
		
	}
