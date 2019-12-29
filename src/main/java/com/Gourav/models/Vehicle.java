package com.Gourav.models;


import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

	@Id
	private String regNo;
	private String vehicleType;
	private String brand;
	private int capacity;
	private Blob rcBook;
	private Blob vPitcure;
	
	@ManyToOne
	private Transporter transporter;
	
	@OneToMany(mappedBy="vehicle")
	private List<Booking> bookings = new ArrayList<Booking>();
}
