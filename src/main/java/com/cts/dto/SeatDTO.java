package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

	private String fightnumber;
	private long id;
	private boolean isavailable;
	private long seatnumber; 
	private String seatclass;
}
