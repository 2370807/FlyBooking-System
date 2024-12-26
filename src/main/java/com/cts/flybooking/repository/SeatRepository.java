package com.cts.flybooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flybooking.model.Flight;
import com.cts.flybooking.model.Passenger;
import com.cts.flybooking.model.Seat;
public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByFlight_FlightnumberAndIsavailable(String flightnumber, boolean b);

//	List<Seat> findByIsAvailableAndPrices(long price,boolean b);
	//List<Seat> findBy

	void deleteBySeatnumber(long seatnumber);

	List<Seat> findByFlight_Flightnumber(String flightnumber);

	Optional<Seat> findBySeatnumber(long seatnumber);
}
