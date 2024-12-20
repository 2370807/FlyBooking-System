package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser_Id(long userId);
}
