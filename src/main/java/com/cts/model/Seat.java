package com.cts.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Seat_Details")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long seatnumber;
	
	//private String seatclass;
	//@Builder.Default
	private boolean isavailable=true;//--changed
	
	@ManyToOne
	@JoinColumn(name="flightnumber")
	@JsonBackReference //-changed
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name="priceid")
	private Price prices;
	
//	@PrePersist
//	void prePersist()
//	{
//		this.isAvailable=true;
//	}
	
//	@ManyToOne
//	@JoinColumn(name="seat_class_id",nullable=false)
//	private SeatClass seatClass;
	
	
}
