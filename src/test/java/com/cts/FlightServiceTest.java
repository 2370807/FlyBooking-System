package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.dto.FlightDTO;
import com.cts.model.Flight;
import com.cts.repository.FlightRepository;
import com.cts.service.FlightServiceImpl;

@SpringBootTest
public class FlightServiceTest {
		
		@Autowired
		private FlightServiceImpl flightService;  
		
		@Autowired
		private FlightRepository flightRepository;
		
		@Test 
		public void testCreateFlight() 
		{ 
			FlightDTO flightDTO = new FlightDTO();
			flightDTO.setFlightnumber("123"); 
			flightDTO.setAirline("Test Airline"); 
			flightDTO.setArrival_time((LocalTime.of(10, 0))); 
			flightDTO.setDeparture_time((LocalTime.of(12, 0))); 
			flightDTO.setDesitination("Desitination");
			flightDTO.setSource("Source"); 
			ResponseEntity<String> response = flightService.createflight(flightDTO); 
			assertEquals(HttpStatus.OK, response.getStatusCode()); 
			assertEquals("Flight added to list Successfully", response.getBody()); 
			Flight flight = flightRepository.findByFlightnumber("123").get(); 
			assertNotNull(flight); 
			assertEquals("123", flight.getFlightnumber()); 
		}
}