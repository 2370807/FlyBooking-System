package com.cts.flybooking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.flybooking.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{
	Optional<Flight> findByFlightnumber(String flightnumber);
	
	//@Query("SELECT f FROM Flight f WHERE f.source = :source AND f.desination = :desination")
	List<Flight> findBySourceAndDestination(String source,String desination);

	@Query("SELECT DISTINCT f.source FROM Flight f")
	List<String> findDistinctSource();

	@Query("SELECT DISTINCT f.destination FROM Flight f")
	List<String> findDistinctDestination();

	@Query("SELECT f FROM Flight f WHERE f.source = :source AND f.destination = :destination AND DATE(f.departure_time) = :departureDate")
    List<Flight> findBySourceAndDestinationAndDepartureTime(
        @Param("source") String source,
        @Param("destination") String destination,
        @Param("departureDate") LocalDate departureDate);
	
	@Query("SELECT f FROM Flight f WHERE f.departure_time >= :now")
	List<Flight> findByDepartureTimeGreaterThanEqual(@Param("now") LocalDateTime now);
}
