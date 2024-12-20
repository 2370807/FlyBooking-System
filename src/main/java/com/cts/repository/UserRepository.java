package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Passenger;

public interface UserRepository extends JpaRepository<Passenger,Long> {

	Passenger findByUseremailAndPassword(String useremail,String password);
	Passenger findByUsername(String username);
}
