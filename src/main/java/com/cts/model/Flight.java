package com.cts.model;

import java.time.LocalTime;
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
	private String desination;
	private LocalTime departure_time;
	private LocalTime arrival_time;
	
	@OneToMany(mappedBy="flight")
	@JsonManagedReference
	private List<Seat> seats;
	
	
}