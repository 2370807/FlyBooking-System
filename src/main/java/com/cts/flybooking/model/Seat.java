package com.cts.flybooking.model;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name="priceid")
	@JsonBackReference
	private Price price;
	
	@OneToMany(mappedBy="seat", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Booking> bookings;
//	@PrePersist
//	void prePersist()
//	{
//		this.isAvailable=true;
//	}
	
//	@ManyToOne
//	@JoinColumn(name="seat_class_id",nullable=false)
//	private SeatClass seatClass;
	
	
}
