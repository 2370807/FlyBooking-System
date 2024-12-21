package com.cts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Seat;
public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByFlight_FlightnumberAndIsavailable(String flightnumber, boolean b);

//	List<Seat> findByIsAvailableAndPrices(long price,boolean b);
	//List<Seat> findBy

	void deleteBySeatnumber(long seatnumber);
}
