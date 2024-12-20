package com.cts.model;

import java.time.LocalDate;
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
	private Passenger user; 
	
	@ManyToOne 
	@JoinColumn(name = "flight_id", nullable = false) 
	private Flight flight; 
	
	@ManyToOne 
	@JoinColumn(name = "seat_id", nullable = false) 
	private Seat seat; 
	
	private LocalDate bookingDate; 
	private String status;
}
