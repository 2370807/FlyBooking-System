package com.cts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

	@NotBlank
	public String username;
	
	@NotBlank
	@Email
	public String useremail;
	
	@NotBlank
	@Pattern(regexp = "^\\d{10}$",message="Mobile number should be 10 digits")
	public long phonenumber;
	
	@NotBlank
	public String name;
	
	@NotBlank
	public String password;
	
	@NotBlank
	public String roles;
}
