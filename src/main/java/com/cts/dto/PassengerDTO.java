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
	public String phonenumber;
	
	@NotBlank(message="The name cannot be empty")
	public String name;
	
	@NotBlank
	@Pattern( regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
	message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
	public String password;
	
	@NotBlank(message="The role Allocation is mandatory")
	public String roles;
}
