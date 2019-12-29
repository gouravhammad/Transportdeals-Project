package com.Gourav.DAO;

import java.sql.Date;
import java.util.List;

import com.Gourav.models.Booking;

public interface BookingDAO {

	 public void addDeal(Booking booking);
	 public List<Booking> getAllDealsByRegNo(String email);
	 public void updateDeal(Booking booking);
	 public void deleteDeal(int bookingCode);
	 public Booking getBookingById(int bookingCode);
	 public List<Booking> getAllDeals();
	 public List<Booking> getAllDealsByCityRange(String sourceCity, String destinationCity);
	 public List<Booking> getAllDealsByDate(Date date);
	 public List<Booking> getAllDealsByDateRange(Date startDate, Date endDate);
}
