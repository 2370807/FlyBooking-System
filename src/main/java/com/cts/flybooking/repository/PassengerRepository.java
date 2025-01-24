package com.cts.flybooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flybooking.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

	//Passenger findByUseremailAndPassword(String useremail,String password);
	Passenger findByUseremail(String useremail);

	Optional<Passenger> findByUsername(String username);

	boolean existsByUsername(String username);
}
