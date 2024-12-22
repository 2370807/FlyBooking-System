package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.dto.PassengerDTO;
import com.cts.model.Passenger;
import com.cts.repository.PassengerRepository;
@Service
public class PassengerServiceImpl implements PassengerService {
	
	private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);
	
	@Autowired
	public PassengerRepository userRepository;
	
	@Autowired
	public PasswordEncoder passwordEncode;
	
	@Override
	public List<Passenger> findAll() {
		logger.info("Fetching all passengers");
		return userRepository.findAll();
	}

	@Override
	public Optional<Passenger> findbyId(long userId) {
		logger.info("Finding passenger by id: {}", userId);
		return userRepository.findById(userId);
	}

	@Override
	public Passenger update(long userId,PassengerDTO userdto) //--changed
	{
		logger.info("Updating passenger with id: {}", userId);
		Passenger user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("Passenger not found"));
		
		user = Passenger.builder()
			 	.name(userdto.getName())
			 	.useremail(userdto.getUseremail())
			 	.phonenumber(userdto.getPhonenumber())
			 	.password(userdto.getPassword())
			 	.username(userdto.getUsername())
			 	.roles(userdto.getRoles())
			 	.build();
		logger.info("Passenger with id: {} updated successfully", userId);
		return userRepository.save(user);
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		logger.info("Deleting passenger with id: {}", userId);
		userRepository.deleteById(userId);
		logger.info("Passenger with id: {} deleted successfully", userId);
		
	}

	@Override
	public ResponseEntity<String> createUser(String username,String useremail,String password,PassengerDTO userdto) {
		logger.info("Creating user with username: {}, useremail: {}", username, useremail);
		if(userRepository.findByUseremail(useremail)!=null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
		}
		if(userRepository.findByUsername(username)!=null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
		}
		Passenger user= Passenger.builder()
				.username(username)
				.useremail(useremail)
				.password(passwordEncode.encode(password))
				.phonenumber(userdto.getPhonenumber())
				.name(userdto.getName())
				.roles(userdto.getRoles())
				.build();
		userRepository.save(user);
		logger.info("User with username: {} created successfully", username);
		return ResponseEntity.status(HttpStatus.OK).body("Registeration successful");
	}
	
	@Override
	public ResponseEntity<String> loginUser(String username, String password) {
		// TODO Auto-generated method stub
		logger.info("User login attempt with username: {}",username);
		Passenger user =userRepository.findByUsername(username);
		if(user==null)
		{
			return ResponseEntity.status(404).body("User not found please register");
		}
		boolean passwordmatch =passwordEncode.matches(password,user.getPassword());
		if(passwordmatch)
		{
			logger.info("Login successful for username: {}",username);
			return ResponseEntity.ok("Login successful");
		}
		else {
			logger.warn("Invalid password for username: {}", username);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid useremail or password");}
	}


}
