package com.cts.flybooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cts.flybooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser_Id(long userId);

	List<Booking> findByFlight_Flightnumber(String flightnumber);

//	@Modifying 
//	@Transactional
//	@Query("Delete from booking b where b.flight.flightnumber= :flightnumber")
//	void deleteByFlight(@Param("flightnumber") String flightnumber);
}
