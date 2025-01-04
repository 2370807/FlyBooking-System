package com.cts.flybooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Price_details")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double price;
	
	private String classname;
	
	@OneToMany(mappedBy="price",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Seat> seats;
}
