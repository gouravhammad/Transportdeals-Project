package com.Gourav.controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.Gourav.DAO.BookingDAO;
import com.Gourav.DAO.VehicleDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Booking;

@Controller
public class BookingController {
  
	 @Autowired
	 private BookingDAO bookingDAO;
	 
	 @Autowired
	 private VehicleDAO vehicleDAO;
	 
	 @Autowired
	 private DataProvider dataProvider;
	 
	 @RequestMapping("addDeal")
	 public ModelAndView showAddBookingForm(@SessionAttribute("email") String email)
     {
		 List<String> cities = dataProvider.getAllCities();
		 List<String> regNos = vehicleDAO.getAllRegNoByEmail(email);
		 
		 ModelAndView modelAndView = new ModelAndView("PostDealForm");
		 modelAndView.addObject("booking",new Booking());
		 modelAndView.addObject("cities",cities);
		 modelAndView.addObject("regNos",regNos);
		 
		 return modelAndView;
	 }
	 
	 @RequestMapping("saveDeal")
	 public ModelAndView saveDeal(@ModelAttribute("booking") Booking booking)
	 { 
		 bookingDAO.addDeal(booking); 
		 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 modelAndView.addObject("error","Deal Posted!!");
		 modelAndView.addObject("path","transporterHome");
		 
	     return modelAndView;
	 }
	 
	 @RequestMapping("viewAllTransporterDeals")
	 public ModelAndView viewAllTransporterDeals(@SessionAttribute("email") String email)
	 {
		 List<Booking> deals = bookingDAO.getAllDealsByRegNo(email);
		 ModelAndView modelAndView = new ModelAndView("PostedDealsList");
		 modelAndView.addObject("deals",deals);
		 
		 return modelAndView;
	 }
	 
	 @RequestMapping("updateDeal")
	 public ModelAndView showDealForm(@SessionAttribute("email") String email, @RequestParam("bookingCode") int bookingCode)
	 { 
		 List<String> cities = dataProvider.getAllCities();
		 List<String> regNos = vehicleDAO.getAllRegNoByEmail(email);
		 
		 Booking booking = bookingDAO.getBookingById(bookingCode); 
		 ModelAndView modelAndView = new ModelAndView("PostDealUpdateForm");
		 modelAndView.addObject("booking",booking);
		 modelAndView.addObject("cities",cities);
		 modelAndView.addObject("regNos",regNos);
		 
	     return modelAndView;
	 }
	 
	 @RequestMapping("saveDealChanges")
	 public ModelAndView saveDealChanges(@ModelAttribute("booking") Booking booking)
	 { 
		 bookingDAO.updateDeal(booking); 
		 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 modelAndView.addObject("error","Deal Updated!!");
		 modelAndView.addObject("path","transporterHome");
		 
	     return modelAndView;
	 }
	 
	 @RequestMapping("removeDeal")
	 public ModelAndView deleteDeal(@RequestParam("bookingCode") int bookingCode)
	 {
		 bookingDAO.deleteDeal(bookingCode);
		 
		 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 modelAndView.addObject("error","Deal Updated!!");
		 modelAndView.addObject("path","transporterHome");
		 
	     return modelAndView; 
	 }
	 
	 @RequestMapping("viewAllDeals")
	 public ModelAndView viewAllDeals()
	 {
		 List<Booking> deals = bookingDAO.getAllDeals();
		 ModelAndView modelAndView = new ModelAndView("DealsList");
		 modelAndView.addObject("deals",deals);
		 
		 return modelAndView;
	 }
	 
	 @RequestMapping("searchDealByCityRange")
	 public ModelAndView showSearchDealCityRangeForm()
	 {
		    ModelAndView modelAndView = new ModelAndView("SearchDealCityRangeForm");
	   		
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
			
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
	   		modelAndView.addObject("booking",new Booking());
	   		
	   		return modelAndView; 
	 }
	 
	 
	 @RequestMapping("saveSearchDealCityRange")
	 public ModelAndView showDealRangeWise(@ModelAttribute("booking") Booking booking)
	 {
		 String sourceCity = booking.getSourceCity();
		 String destinationCity = booking.getDestinationCity();
		 
		 List<Booking> deals = bookingDAO.getAllDealsByCityRange(sourceCity,destinationCity);
		 ModelAndView modelAndView = new ModelAndView("DealsList");
		 modelAndView.addObject("deals",deals);
		 
		 return modelAndView;
	 }
	 
	 @RequestMapping("searchDealByDate")
	 public String showSearchDealByDateForm()
	 {
		 return "SearchDealDateForm";
	 }
	 
	 @RequestMapping("saveSearchDealDate")
	 public ModelAndView showDealsByDate(@RequestParam("date") Date date)
	 {
		 List<Booking> deals = bookingDAO.getAllDealsByDate(date);
		 ModelAndView modelAndView = new ModelAndView("DealsList");
		 modelAndView.addObject("deals",deals);
		 
		 return modelAndView;
	 }
	 
	 @RequestMapping("searchDealByDateRange")
	 public String showSearchDealByDateRangeForm()
	 {
		 return "SearchDealDateRangeForm";
	 }
	 
	 @RequestMapping("saveSearchDealDateRange")
	 public ModelAndView showDealsByDateRange(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate)
	 {
		 List<Booking> deals = bookingDAO.getAllDealsByDateRange(startDate,endDate);
		 ModelAndView modelAndView = new ModelAndView("DealsList");
		 modelAndView.addObject("deals",deals);
		 
		 return modelAndView;
	 }
}
