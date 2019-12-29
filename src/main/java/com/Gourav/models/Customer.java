package com.Gourav.models;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	private String email;
	private String userName;
	private String password;
	private int mobile;
	private String residentialAddress;
	private String state;
	private String city;
	private Blob profilePicture;
	
}
