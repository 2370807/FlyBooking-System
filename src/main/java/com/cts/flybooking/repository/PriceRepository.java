package com.cts.flybooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flybooking.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

	void deleteByClassname(String classname);

	Optional<Price> findByClassname(String classname);

}
