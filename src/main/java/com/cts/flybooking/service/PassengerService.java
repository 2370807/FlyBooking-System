package com.cts.flybooking.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.PassengerDTO;
import com.cts.flybooking.dto.PassengerUpdateDTO;
import com.cts.flybooking.model.Passenger;

public interface PassengerService {

	public List<Passenger> findAll();

	public Passenger findbyId(long userId);

	public Passenger update(long userId,PassengerUpdateDTO user);

	public ResponseEntity<?> loginUser(String username, String password);

	public void delete(long userId);

	public ResponseEntity<String> createUser(PassengerDTO userdto);
	
	
}
