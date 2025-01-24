package com.cts.flybooking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	//@Transactional
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
	
	@GetMapping("/flightbysourceanddestination/{source}/{desination}")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<Flight> findbysourceAnddesination(@PathVariable String source,@PathVariable String desination)
	{
		return flightService.findFlightBySourceAndDesitnation(source, desination);
	}
	
	@GetMapping("/sources")
    public ResponseEntity<List<String>> getAllSources() {
       return flightService.getAllFlightFromSource();
    }

    // Get all unique destinations
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getAllDestinations() {
        return flightService.getAllFlightFromDestinations();
    }
    
    @GetMapping("/flightbysourceanddestinationanddate/{source}/{destination}/{departureDate}")
    public List<Flight> findFlightsBySourceDestinationAndDate(
        @PathVariable String source,
        @PathVariable String destination,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        return flightService.findFlightsBySourceDestinationAndDate(source, destination, departureDate);
    }
}
