package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.dto.PassengerDTO;
import com.cts.model.Passenger;
import com.cts.repository.PassengerRepository;
@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	public PassengerRepository userRepository;
	
	@Override
	public List<Passenger> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<Passenger> findbyId(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Passenger update(long userId,PassengerDTO userdto) //--changed
	{
		Passenger user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("Passenger not found"));
		
		user = Passenger.builder()
			 	.name(userdto.getName())
			 	.useremail(userdto.getUseremail())
			 	.phonenumber(userdto.getPhonenumber())
			 	.password(userdto.getPassword())
			 	.username(userdto.getUsername())
			 	.roles(userdto.getRoles())
			 	.build();
		return userRepository.save(user);
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		
	}

	@Override
	public ResponseEntity<String> createUser(String username,String useremail,String password,PassengerDTO userdto) {
		
		if(userRepository.findByUseremailAndPassword(useremail,password)!=null)
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
				.password(password)
				.phonenumber(userdto.getPhonenumber())
				.name(userdto.getName())
				.roles(userdto.getRoles())
				.build();
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("Registeration successful");
	}
	
	@Override
	public ResponseEntity<String> loginUser(String useremail, String password) {
		// TODO Auto-generated method stub
		Passenger user =userRepository.findByUseremailAndPassword(useremail,password);
		if(user==null)
		{
			return ResponseEntity.status(404).body("User not found please register");
		}
		if(user.getPassword().equals(password) && user!=null)
		{
			return ResponseEntity.ok("Login successful");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	}


}
