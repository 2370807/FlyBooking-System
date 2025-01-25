package com.cts.flybooking.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
	
	 private String token;
	 private String role;
	 private long user_id;
}
