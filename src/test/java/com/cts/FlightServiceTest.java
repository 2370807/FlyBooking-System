package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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