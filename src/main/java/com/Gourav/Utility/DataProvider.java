package com.Gourav.Utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DataProvider {

	private List<String> cities = new ArrayList<String>();
	private List<String> states = new ArrayList<String>();
	private List<String> brands = new ArrayList<String>();
	private List<String> vTypes = new ArrayList<String>();
	
	public List<String> getAllCities()
	{
		cities.add("Indore");
		cities.add("Mhow");
		cities.add("Gwalior");
		cities.add("Jaipur");
		cities.add("Surat");
		cities.add("Mumbai");
		cities.add("Kanpur");
		cities.add("Nagpur");
		cities.add("Bhopal");
		cities.add("Sagar");
		
		return cities;
	}
	
	public List<String> getAllStates()
	{
		states.add("Delhi");
		states.add("Maharastra");
		states.add("Madhya Pradesh");
		states.add("Uttar Pradesh");
		states.add("Chattisgarh");
		states.add("Gujrat");
		states.add("Goa");
		states.add("Bihar");
		states.add("West Bangal");
		states.add("Rajasthan");
		
		return states;
	}
	
	public List<String> getAllBrands()
	{
		brands.add("Tata");
		brands.add("Mahindra");
		brands.add("Eicher");
		brands.add("Ashok Leyland");
		brands.add("Atul");
		brands.add("Mazda");
		
		return brands;
	}
	
	public List<String> getAllvTypes()
	{
		vTypes.add("Container");
		vTypes.add("Trolly");
		vTypes.add("Tanker");
		vTypes.add("Large-Truck");
		vTypes.add("Mini-Truck");
		vTypes.add("Dumper");
		
		return vTypes;
	}
	
}
