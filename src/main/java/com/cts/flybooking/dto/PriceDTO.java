package com.cts.flybooking.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
	private double price;
	
	@NotBlank(message="class name should be given")
	private String classname;
	
	@Min(1)
	private int no_of_seats;
}
