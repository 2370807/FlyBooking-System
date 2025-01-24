package com.cts.flybooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispSeatDTO {
	
	private long seatId;
	
	private long seatnumber;
	
	private boolean isavailable;
	
	private double price;
	
	private String classname;
	
}
