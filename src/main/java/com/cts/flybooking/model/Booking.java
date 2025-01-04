package com.cts.flybooking.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Booking_Details")
public class Booking {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id; 
	
	@ManyToOne 
	@JoinColumn(name = "user_id", nullable = false) 
	@JsonBackReference
	private Passenger user; 
	
	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false) 
	@JsonBackReference
	private Flight flight; 
	
	@ManyToOne 
	@JoinColumn(name = "seat_id", nullable = false) 
	@JsonBackReference
	private Seat seat; 
	
	private int no_of_seats;
	private LocalDate bookingDate; 
	private String status;
}
