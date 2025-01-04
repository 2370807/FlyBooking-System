package com.cts.flybooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User_details")
@Builder
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public String username;
	
	public String useremail;
	
	public String phonenumber;
	
	public String name;
	
	public String password;
	
	public String roles;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Booking> bookings;
	
	@PrePersist
	void PrePersist()
	{
		if(roles==null)
		{
			roles="ROLE_USER";
		}
	}
}
