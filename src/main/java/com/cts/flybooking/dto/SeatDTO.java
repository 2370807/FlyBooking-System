package com.cts.flybooking.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

	@NotBlank(message="flight number cannot be null")
	private String fightnumber;
//	@NotBlank(message="Enter valid class id")
//	private String id;
	//@NotBlank(message="Either enter true or false")
	private boolean isavailable;
	
	@NotBlank(message="Enter the valid seatnumber")
	private String seatnumber;
	
	@NotBlank(message="Enter valid Seatclass")
	private String seatclass;
}
