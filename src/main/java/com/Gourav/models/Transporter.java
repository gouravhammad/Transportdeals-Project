package com.Gourav.models;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transporter 
{
	@Id
	private String email;
	private String transporterName;
	private String password;
	private int mobile;
	private String residentialAddress;
	private String state;
	private String city;
	private Blob profilePicture;
	
	@OneToMany(mappedBy="transporter")
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

}	
