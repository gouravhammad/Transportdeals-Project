package com.Gourav.DAO;

import java.util.List;

import com.Gourav.models.Vehicle;

public interface VehicleDAO
{
	
	  public void addVehicle(Vehicle vehicle);
	  public void removeVehicle(String regNo);
	  public void updateVehicle(Vehicle vehicle);
      public List<Vehicle> getAllVehicleByEmail(String email);
	  public Vehicle getVehicleByRegNo(String regNo);
	  public List<String> getAllRegNoByEmail(String email);
      
}
