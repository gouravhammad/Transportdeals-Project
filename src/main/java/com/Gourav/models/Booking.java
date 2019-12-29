package com.Gourav.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Booking 
{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   int bookingCode;
   private String sourceCity;
   private String destinationCity;
   private Date date;
   private int fare;
   
   @ManyToOne
   private Vehicle vehicle;
}
