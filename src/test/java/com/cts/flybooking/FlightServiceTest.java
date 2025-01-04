package com.cts.flybooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.flybooking.dto.FlightDTO;
import com.cts.flybooking.model.Flight;
import com.cts.flybooking.repository.FlightRepository;
import com.cts.flybooking.service.FlightServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
		
		@InjectMocks
		private FlightServiceImpl flightService;  
		
		@Mock
		private FlightRepository flightRepository;
		
		
		private FlightDTO flightDTO;
		private Flight flight;
		
		@BeforeEach
		public void settingFlight()
		{
			flightDTO = new FlightDTO();
			flightDTO.setFlightnumber("123"); 
			flightDTO.setAirline("Test Airline"); 
			flightDTO.setArrival_time((LocalDateTime.of(2024, 12, 25, 10, 30))); 
			flightDTO.setDeparture_time((LocalDateTime.of(2024, 12, 25, 12, 30))); 
			flightDTO.setDesitination("Desitination");
			flightDTO.setSource("Source"); 
			
			flight = new Flight();
			flight.setFlightnumber("123"); 
			flight.setAirline("Test Airline"); 
			flight.setArrival_time((LocalDateTime.of(2024, 12, 25, 10, 30))); 
			flight.setDeparture_time((LocalDateTime.of(2024, 12, 25, 12, 30))); 
			flight.setDesination("Desitination");
			flight.setSource("Source"); 
		}
		
		@Test 
		public void testCreateFlight() 
		{ 
			when(flightRepository.save(any(Flight.class))).thenReturn(flight);
			when(flightRepository.findByFlightnumber(flightDTO.getFlightnumber())).thenReturn(Optional.of(flight));
	
			ResponseEntity<String> response = flightService.createflight(flightDTO); 
			assertEquals(HttpStatus.OK, response.getStatusCode()); 
			assertEquals("Flight added to list Successfully", response.getBody()); 
			Flight savedFlight=flightRepository.findByFlightnumber(flightDTO.getFlightnumber()).orElse(null);
			assertNotNull(savedFlight); 
			assertEquals(flightDTO.getFlightnumber(), flight.getFlightnumber()); 
		}
}