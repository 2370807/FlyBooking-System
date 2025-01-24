package com.cts.flybooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
	
	@NotNull(message="The Username must be present")
	private String username;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
	message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
	private String password;
}
