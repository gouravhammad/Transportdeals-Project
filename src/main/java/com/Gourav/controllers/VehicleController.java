package com.Gourav.controllers;

import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Gourav.DAO.VehicleDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Transporter;
import com.Gourav.models.Vehicle;

@Controller
public class VehicleController
{
	   @Autowired
       private VehicleDAO vehicleDAO;
       
	   @Autowired
	   private DataProvider dataProvider;
	   
	   @RequestMapping("LoadVehiclePic")
	   public void loadVehicleImage(@RequestParam("regNo") String regNo, HttpServletResponse response)
		{
			response.setContentType("image/png,image/jpeg,image/jpg");
			Vehicle vehicle = vehicleDAO.getVehicleByRegNo(regNo);
			Blob blob = vehicle.getVPitcure();
			
			try
			{
				byte b[] = blob.getBytes(1,(int)blob.length());
				ServletOutputStream out = response.getOutputStream();
				out.write(b);
				out.close();
			}
			catch(Exception ex)
			{
				System.out.println("Error in LoadVehiclePicture : " + ex);	
			}
	   }
	   
	   @RequestMapping("LoadRCBook")
	   public void loadRCBook(@RequestParam("regNo") String regNo, HttpServletResponse response)
		{
			response.setContentType("application/pdf");
			Vehicle vehicle = vehicleDAO.getVehicleByRegNo(regNo);
			Blob blob = vehicle.getRcBook();
			
			try
			{
				byte b[] = blob.getBytes(1,(int)blob.length());
				ServletOutputStream out = response.getOutputStream();
				out.write(b);
				out.close();
			}
			catch(Exception ex)
			{
				System.out.println("Error in Load-RC-Book : " + ex);	
			}
	   }
       
       @RequestMapping("addVehicle")
       public ModelAndView showVehicleForm()
       {
    	   ModelAndView modelAndView = new ModelAndView("VehicleForm");
    	   List<String> vTypes = dataProvider.getAllvTypes(); 	   
	       List<String> brands = dataProvider.getAllBrands();
			
	       modelAndView.addObject("vTypes",vTypes);
		   modelAndView.addObject("brands",brands);
    	   modelAndView.addObject("vehicle",new Vehicle());
    	   
    	   return modelAndView;
       }
       
       @RequestMapping("saveVehicle")
       public ModelAndView saveVehicle(@SessionAttribute("email") String email, @ModelAttribute("vehicle") Vehicle vehicle,@RequestParam("vehiclePic") MultipartFile vehiclePic, @RequestParam("rcbook") MultipartFile rcBook)
       {
    	   try
    	   {
    		   byte b1[] = vehiclePic.getBytes();
    		   Blob blob1 = BlobProxy.generateProxy(b1);
    		   vehicle.setVPitcure(blob1);
    		   
    		   byte b2[] = rcBook.getBytes();
    		   Blob blob2 = BlobProxy.generateProxy(b2);
    		   vehicle.setRcBook(blob2);
    	   }
    	   catch(Exception Ex)
    	   {
    		   System.out.println("Error in Vehicle vehiclePicture : " + Ex);
    	   }
    	   
    	   Transporter transporter = new Transporter();
    	   transporter.setEmail(email);
    	   vehicle.setTransporter(transporter);
    	   
    	   vehicleDAO.addVehicle(vehicle);
    	   
    	   ModelAndView modelAndView = new ModelAndView("AlertMessage");
 		   modelAndView.addObject("error","Vehicle Added!!");
 		   modelAndView.addObject("path","transporterHome");
 	       return modelAndView;
       }
       
       @RequestMapping("updateVehicle")
       public ModelAndView updateVehicleForm(@RequestParam("regNo") String regNo)
       {
    	   Vehicle vehicle = vehicleDAO.getVehicleByRegNo(regNo);
    	   List<String> vTypes = dataProvider.getAllvTypes(); 	   
	       List<String> brands = dataProvider.getAllBrands();
			
    	   ModelAndView modelAndView = new ModelAndView("VehicleUpdateForm");
	       modelAndView.addObject("vTypes",vTypes);
		   modelAndView.addObject("brands",brands);
    	   modelAndView.addObject("vehicle",vehicle);
    	   
    	   return modelAndView;
       }
       
       @RequestMapping("saveVehicleChanges")
       public ModelAndView saveVehicleChanges(@SessionAttribute("email") String email, @ModelAttribute("vehicle") Vehicle vehicle,@RequestParam("vehiclePic") MultipartFile vehiclePic, @RequestParam("rcbook") MultipartFile rcBook)
       {
    	   try
    	   {
    		   byte b1[] = vehiclePic.getBytes();
    		   Blob blob1 = BlobProxy.generateProxy(b1);
    		   vehicle.setVPitcure(blob1);
    		   
    		   byte b2[] = rcBook.getBytes();
    		   Blob blob2 = BlobProxy.generateProxy(b2);
    		   vehicle.setRcBook(blob2);
    	   }
    	   catch(Exception Ex)
    	   {
    		   System.out.println("Error in Vehicle vehiclePicture : " + Ex);
    	   }
    	   
    	   Transporter transporter = new Transporter();
    	   transporter.setEmail(email);
    	   vehicle.setTransporter(transporter);
    	   
    	   vehicleDAO.updateVehicle(vehicle);
    	   
    	   ModelAndView modelAndView = new ModelAndView("AlertMessage");
 		   modelAndView.addObject("error","Vehicle Updated!!");
 		   modelAndView.addObject("path","transporterHome");
 	       return modelAndView;
       }
      
      @RequestMapping("removeVehicle")
      public ModelAndView removeVehicle(@RequestParam("regNo") String regNo)
      {
    	  vehicleDAO.removeVehicle(regNo);
    	 
    	  ModelAndView modelAndView = new ModelAndView("AlertMessage");
		  modelAndView.addObject("error","Vehicle Removed!!");
		  modelAndView.addObject("path","transporterHome");
	      return modelAndView;
      }
      
      @RequestMapping("viewAllVehicle")
      public ModelAndView viewAllVehicle(@SessionAttribute("email") String email)
      {
    	  List<Vehicle> vehicles = vehicleDAO.getAllVehicleByEmail(email);
 
    	  ModelAndView modelAndView = new ModelAndView("VehicleList");
    	  modelAndView.addObject("vehicles",vehicles);
    	  return modelAndView;
      }
}
