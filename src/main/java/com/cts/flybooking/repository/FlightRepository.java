package com.cts.flybooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.flybooking.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{
	Optional<Flight> findByFlightnumber(String flightnumber);
	
	//@Query("SELECT f FROM Flight f WHERE f.source = :source AND f.desination = :desination")
	List<Flight> findBySourceAndDesination(String source,String desination);
}
