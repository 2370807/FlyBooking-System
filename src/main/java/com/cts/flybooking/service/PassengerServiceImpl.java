package com.cts.flybooking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cts.flybooking.dto.LoginResponseDTO;
import com.cts.flybooking.dto.PassengerDTO;
import com.cts.flybooking.dto.PassengerUpdateDTO;
import com.cts.flybooking.model.Passenger;
import com.cts.flybooking.repository.PassengerRepository;
import com.cts.flybooking.security.JwtUtils;
@Service
public class PassengerServiceImpl implements PassengerService {
	
	private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);
	
	@Autowired
	public PassengerRepository userRepository;
	
	@Autowired
	public PasswordEncoder passwordEncode;
	
	@Autowired
	public JwtUtils jwtUtils;
	
	@Override
	public List<Passenger> findAll() {
		logger.info("Fetching all passengers");
		return userRepository.findAll();
	}

	@Override
	public Passenger findbyId(long userId) {
		logger.info("Finding passenger by id: {}", userId);
		return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("No Passenger with Id: "+userId+" is found"));
	}

	@Override
	public Passenger update(long userId,PassengerUpdateDTO userdto) //--changed
	{
		logger.info("Updating passenger with id: {}", userId);
		Passenger user=userRepository.findById(userId)
				.orElseThrow(()->new RuntimeException("Passenger not found"));
		user.setName(userdto.getName());
		user.setPhonenumber(userdto.getPhonenumber());
		user.setUseremail(userdto.getUseremail());
		user.setUsername(userdto.getUsername());
		user.setRoles(userdto.getRoles());
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
	public ResponseEntity<String> createUser(PassengerDTO userdto) {
		logger.info("Creating user with username: {}, useremail: {}", userdto.getUsername(), userdto.getUseremail());
		if(userRepository.findByUseremail(userdto.getUseremail())!=null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
		}
		if(userRepository.existsByUsername(userdto.getUsername()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
		}
		Passenger user= Passenger.builder()
				.username(userdto.getUsername())
				.useremail(userdto.getUseremail())
				.password(passwordEncode.encode(userdto.getPassword()))
				.phonenumber(userdto.getPhonenumber())
				.name(userdto.getName())
				.roles(userdto.getRoles())
				.build();
		userRepository.save(user);
		logger.info("User with username: {} created successfully", userdto.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body("Registeration successful");
		
	}
	
	@Override
	public ResponseEntity<?> loginUser(String username, String password) {
		// TODO Auto-generated method stub
		logger.info("User login attempt with username: {}",username);
		Passenger user =userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not foud please register"));
//		if(user==null)
//		{
//			return ResponseEntity.status(404).body("User not found please register");
//		}
		boolean passwordmatch =passwordEncode.matches(password,user.getPassword());
		if(passwordmatch)
		{
			logger.info("Login successful for username: {}",username);
			String token= jwtUtils.generateToken(user);
			LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setToken(token);
			loginResponseDTO.setRole(user.getRoles());
			loginResponseDTO.setUser_id(user.getId());
			return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
		}
		else {
			logger.warn("Invalid password for username: {}", username);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);}
	}


}
