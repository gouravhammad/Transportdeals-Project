package com.Gourav.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class OTP {
 
	@Id
	@Column(columnDefinition="int default 347692")
	private int OTPNumber;
	
}
