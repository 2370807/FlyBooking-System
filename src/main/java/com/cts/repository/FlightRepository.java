package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{
	Optional<Flight> findByFlightnumber(String flightnumber);
	Flight findBySourceAndDesination(String source,String desination);
}
