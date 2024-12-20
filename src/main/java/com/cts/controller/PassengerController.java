 package com.cts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.PassengerDTO;
import com.cts.model.Passenger;
import com.cts.service.PassengerService;

@RestController
@RequestMapping("/User")
public class PassengerController {
	
	 @Autowired
	 private PassengerService userService;
	
	 
	 @GetMapping
	 public List<Passenger> getAllUser()
	 {
		return userService.findAll();
	 }
	 
	 
	 @GetMapping("/GetById/{id}")
	 public Optional<Passenger> getUserbyId(@PathVariable("id") Long userId)
	 {
		 return userService.findbyId(userId);
	 }
	 
	 
	 @PutMapping("/Updateuser/{id}")
	 public Passenger updateUser(@PathVariable("id") Long userId, @RequestBody PassengerDTO userdto)
	 {
		 return userService.update(userId,userdto);
	 }
	 
	 
	 @PostMapping("/register/{username}/{useremail}/{password}")
	 public ResponseEntity<String> registerNewUser(@PathVariable("username") String username,@PathVariable("useremail") String useremail,@PathVariable("password") String password,@RequestBody PassengerDTO userdto)
	 { 
		return  userService.createUser(username,useremail,password,userdto);
	 }
	 
	 
	 @GetMapping("/login/{useremail}/{password}")
	 public ResponseEntity<String> loginUser(@PathVariable("useremail") String useremail,@PathVariable("password") String password)
	 {
		 return userService.loginUser(useremail,password);
	 }
	 
	 
	 @DeleteMapping("/remove/{id}")
	 public void Removeuser(@PathVariable("id") Long userId)
	 {
		 userService.delete(userId);
	 }
}
