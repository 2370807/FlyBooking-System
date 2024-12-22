package com.cts.flybooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.PassengerDTO;
import com.cts.flybooking.model.Passenger;

public interface PassengerService {

	public List<Passenger> findAll();

	public Optional<Passenger> findbyId(long userId);

	public Passenger update(long userId,PassengerDTO user);

	public ResponseEntity<String> loginUser(String username, String password);

	public void delete(long userId);

	public ResponseEntity<String> createUser(String username,String Username, String password,PassengerDTO userdto);
	
}
