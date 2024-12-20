package com.cts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.PriceDTO;
import com.cts.model.Price;
import com.cts.service.PriceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seatclass")
public class PriceController {

	@Autowired
	private PriceService  priceService;
	
	@PostMapping("/newclass")
	public ResponseEntity<String> addnewClass(@RequestBody @Valid PriceDTO pricedto)
	{
		priceService.createclass(pricedto);
		return ResponseEntity.status(HttpStatus.OK).body("SeatClass Added Successfully");
	}
	
	@PutMapping("/updateclass/{id}")
	public ResponseEntity<String> updateprice(@PathVariable long id,@RequestBody @Valid PriceDTO pricedto)
	{
		priceService.updateclassandprice(id, pricedto);
		return ResponseEntity.status(HttpStatus.OK).body("Updated seat successfully");
	}
	
	@DeleteMapping("/deleteClass/{classname}")
	public ResponseEntity<String> deleteClass(@PathVariable String classname)
	{
		priceService.removeClass(classname);
		return ResponseEntity.status(HttpStatus.OK).body("SeatClass Removed Successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<Price>> allSeatClass()
	{
		return ResponseEntity.status(HttpStatus.OK).body(priceService.getAll());
	}
	
	@GetMapping("/byclassname/{classname}")
	public ResponseEntity<Price> seatClassbyclass(@PathVariable String classname)
	{
		return ResponseEntity.status(HttpStatus.OK).body(priceService.getbyclass(classname).orElseThrow(()->new RuntimeException("Seat Class not Found")));
	}
	
}
