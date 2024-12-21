package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Passenger;

public interface UserRepository extends JpaRepository<Passenger, Long> {

	Optional<Passenger> findByUsername(String username);

}
