package com.cts.flybooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flybooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser_Id(long userId);
}
