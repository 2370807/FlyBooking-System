package com.cts.flybooking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
	

	@Id
	@Column(name="flight_number")
	private String flightnumber;
	private String airline;
	private String source;
	private String destination;
	private LocalDateTime departure_time;
	private LocalDateTime arrival_time;
	
	@OneToMany(mappedBy="flight",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Booking> bookings;
	
	@OneToMany(mappedBy="flight", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Seat> seats;
	
	
}