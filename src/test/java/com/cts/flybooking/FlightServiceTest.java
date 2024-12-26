package com.cts.flybooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.repository.FlightRepository;
import com.cts.flybooking.service.FlightServiceImpl;

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
			flightDTO.setArrival_time((LocalDateTime.of(2024, 12, 25, 10, 30))); 
			flightDTO.setDeparture_time((LocalDateTime.of(2024, 12, 25, 12, 30))); 
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