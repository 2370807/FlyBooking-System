package com.cts.flybooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/allflight")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Flight> findallFlight()
	{
		return flightService.findAllflight();
	}
	
	@PostMapping("/newflight")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> addnewFlight(@RequestBody @Valid FlightDTO flightdto)
	{
		return flightService.createflight(flightdto);
	}
	
	@PutMapping("/updatedetails/{flightid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateFlightAllDetails(@PathVariable String flightid,@RequestBody @Valid FlightDTO flightdto)
	{
		return flightService.updateflight(flightid, flightdto);
	}
	
	@DeleteMapping("/deleteflight/{flightid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> removeFlightdetails(@PathVariable String flightid)
	{
		return flightService.deleteflight(flightid);
	}
	
	@GetMapping("/flightbyid/{flightid}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public Optional<Flight> findbyFlightNumber(@PathVariable String flightid)
	{
		return flightService.findflightById(flightid);
	}
	
	@GetMapping("/flightbysourceanddesination/{source}/{desination}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public Flight findbysourceAnddesination(String source,String desination)
	{
		return flightService.findFlightBySourceAndDesitnation(source, desination);
	}
}
