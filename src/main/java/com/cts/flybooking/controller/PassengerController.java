 package com.cts.flybooking.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flybooking.dto.LoginDTO;
import com.cts.flybooking.dto.PassengerDTO;
import com.cts.flybooking.dto.PassengerUpdateDTO;
import com.cts.flybooking.model.Passenger;
import com.cts.flybooking.service.PassengerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Passenger")
public class PassengerController {
	
	 @Autowired
	 private PassengerService userService;
	
	 
	 @GetMapping
	 public List<Passenger> getAllUser()
	 {
		return userService.findAll();
	 }
	 
	 
	 @GetMapping("/GetById/{id}")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	 public Passenger getUserbyId(@PathVariable("id") Long userId)
	 {
		 return userService.findbyId(userId);
	 }
	 
	 
	 @PutMapping("/UpdatePassenger/{id}")
	 @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	 public Passenger updateUser(@PathVariable("id") Long userId, @RequestBody @Valid  PassengerUpdateDTO userdto)
	 {
		 return userService.update(userId,userdto);
	 }
	 
	 
	 @PostMapping("/register")
	 public ResponseEntity<String> registerNewUser(@RequestBody  @Valid PassengerDTO userdto)
	 { 
		return  userService.createUser(userdto);
	 }
	 
	 
	 @PostMapping("/login")
	 public ResponseEntity<Passenger> loginUser(@RequestBody @Valid LoginDTO logindto)
	 {
		 return userService.loginUser(logindto.getUsername(),logindto.getPassword());
	 }
	 
	 
	 @DeleteMapping("/remove/{id}")
	 public void Removeuser(@PathVariable("id") Long userId)
	 {
		 userService.delete(userId);
	 }
}
