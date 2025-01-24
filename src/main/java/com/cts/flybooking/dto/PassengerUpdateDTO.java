package com.cts.flybooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerUpdateDTO {

	@NotBlank
	public String username;
	
	@NotBlank
	@Email
	public String useremail;
	
	@NotBlank
	@Pattern(regexp = "^\\d{10}$",message="Mobile number should be 10 digits")
	public String phonenumber;
	
	@NotBlank(message="The name cannot be empty")
	public String name;
	
//	@NotBlank(message="The role Allocation is mandatory")
	public String roles;
}
