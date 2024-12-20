package com.cts.dto;

import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cts.model.Seat;

import jakarta.validation.constraints.Future;
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
	private String desitination;
	
	//@NotBlank(message="Departure time cannot be empty")
	@Future(message = "The end time must be in the future")
	private LocalTime departure_time;
	
	//@NotBlank(message="Arrival time cannot be empty")
	@Future(message = "The start time must be in the future")
	private LocalTime arrival_time;
	
}
