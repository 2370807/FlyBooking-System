package com.cts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.FlightDTO;
import com.cts.model.Flight;
import com.cts.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/allflight")
	public List<Flight> findallFlight()
	{
		return flightService.findAllflight();
	}
	
	@PostMapping("/newflight")
	public ResponseEntity<String> addnewFlight(@RequestBody @Valid FlightDTO flightdto)
	{
		return flightService.createflight(flightdto);
	}
	
	@PutMapping("/updatedetails/{flightid}")
	public ResponseEntity<String> updateFlightAllDetails(@PathVariable String flightid,@RequestBody @Valid FlightDTO flightdto)
	{
		return flightService.updateflight(flightid, flightdto);
	}
	
	@DeleteMapping("/deleteflight/{flightid}")
	public ResponseEntity<String> removeFlightdetails(@PathVariable String flightid)
	{
		return flightService.deleteflight(flightid);
	}
	
	@GetMapping("/flightbyid/{flightid}")
	public Optional<Flight> findbyFlightNumber(@PathVariable String flightid)
	{
		return flightService.findflightById(flightid);
	}
	
	@GetMapping("/flightbysourceanddesination/{source}/{desination}")
	public Flight findbysourceAnddesination(String source,String desination)
	{
		return flightService.findFlightBySourceAndDesitnation(source, desination);
	}
}
