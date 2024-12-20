package com.cts.dto;

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
	@NotBlank(message="Enter valid class id")
	private long id;
	//@NotBlank(message="Either enter true or false")
	@AssertTrue(message="The available seat must be true")
	private boolean isavailable;
	@NotBlank(message="Enter the valid seatnumber")
	private long seatnumber;
	@NotBlank(message="Enter valid Seatclass")
	private String seatclass;
}
