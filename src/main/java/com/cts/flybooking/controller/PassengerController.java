 package com.cts.flybooking.controller;

import java.util.List;
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

import com.cts.flybooking.dto.PassengerDTO;
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
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public Optional<Passenger> getUserbyId(@PathVariable("id") Long userId)
	 {
		 return userService.findbyId(userId);
	 }
	 
	 
	 @PutMapping("/UpdatePassenger/{id}")
	 @PreAuthorize("hasRole('ROLE_USER')")
	 public Passenger updateUser(@PathVariable("id") Long userId, @RequestBody @Valid  PassengerDTO userdto)
	 {
		 return userService.update(userId,userdto);
	 }
	 
	 
	 @PostMapping("/register")
	 public ResponseEntity<String> registerNewUser(@RequestBody  @Valid PassengerDTO userdto)
	 { 
		return  userService.createUser(userdto);
	 }
	 
	 
	 @GetMapping("/login/{useremail}/{password}")
	 public ResponseEntity<String> loginUser(@PathVariable("useremail") String useremail,@PathVariable("password") String password)
	 {
		 return userService.loginUser(useremail,password);
	 }
	 
	 
	 @DeleteMapping("/remove/{id}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public void Removeuser(@PathVariable("id") Long userId)
	 {
		 userService.delete(userId);
	 }
}
