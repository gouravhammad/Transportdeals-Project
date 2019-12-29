package com.Gourav.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Gourav.DAO.CommonDAO;
import com.Gourav.DAO.TransporterDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Transporter;

@Controller
@SessionAttributes(names= {"email","realOTP","transporter"})
public class TransporterController
{
	   @Autowired
	   private TransporterDAO transporterDAO;
	
	   @Autowired
	   private CommonDAO commonDAO;
	   
	   @Autowired
	   private DataProvider dataProvider;
	   
	   @RequestMapping("LoadTransporterDP")
	   public void loadTransporterDP(@RequestParam("email") String email, HttpServletResponse response)
		{
			response.setContentType("image/png,image/jpeg,image/jpg");
			Transporter transporter = transporterDAO.getTransporterByEmail(email);
			Blob blob = transporter.getProfilePicture();
			
			try
			{
				byte b[] = blob.getBytes(1,(int)blob.length());
				ServletOutputStream out = response.getOutputStream();
				out.write(b);
				out.close();
			}
			catch(Exception ex)
			{
				System.out.println("Error in LoadTransporterDP : " + ex);	
			}
	   }
	
	   @RequestMapping("transporterSignup")
	   public ModelAndView showTransporterSignupForm()
	   {
	   		ModelAndView modelAndView = new ModelAndView("TransporterSignup");
	   		
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
			
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
	   		modelAndView.addObject("transporter",new Transporter());
	   		
	   		return modelAndView; 
	   }
	       
       @RequestMapping("saveTransporter")
       public ModelAndView saveTransporter(@ModelAttribute("transporter") Transporter transporter, @RequestParam("profilePic") MultipartFile profilePicture)
       {
	       	try
	   		{
	   			byte b[] = profilePicture.getBytes();
	   			Blob blob = BlobProxy.generateProxy(b);
	   			transporter.setProfilePicture(blob);
	   		}
	   		catch(IOException e)
	   		{
	   			System.out.println("Error in Transporter Profile Picture : " + e);
	   		}
	       	
	       	int otpArray[] = new int[1];
	       	
	       	commonDAO.sendMail(transporter.getEmail(),otpArray);
	       	
	       	ModelAndView modelAndView = new ModelAndView("OTPFormTransporter");
	       	modelAndView.addObject("email",transporter.getEmail());
	       	modelAndView.addObject("realOTP",otpArray[0]);
	       	modelAndView.addObject("transporter",transporter);
	       	
	       	return modelAndView;
       }
	
       @RequestMapping("transporterLogin")
       public String showTransporterLoginForm()
       {
    	   return "TransporterLogin";
       }
       
       @RequestMapping("saveTransporterLogin")
       public ModelAndView verifyTransporter(@RequestParam("email") String email,@RequestParam("password") String password)
       {
    	   boolean status = transporterDAO.verifyTransporter(email,password);
    	   
    	   if(status)
    	   {
    		   ModelAndView modelAndView = new ModelAndView("TransporterHome");
    		   modelAndView.addObject("email",email);
    	       return modelAndView;
    	   }
    	   else
    	   {
    		   ModelAndView modelAndView = new ModelAndView("AlertMessage");
     		   modelAndView.addObject("error","INVALID USER");
     		   modelAndView.addObject("path","home");
     	       return modelAndView;
    	   }
       }
       
       @RequestMapping("updateTransporter")
       public ModelAndView showUpdateForm(@SessionAttribute("email") String email)
       {
    	   Transporter transporter = transporterDAO.getTransporterByEmail(email);
    	   ModelAndView modelAndView = new ModelAndView("TransporterUpdateForm");
    	   List<String> cities = dataProvider.getAllCities();
	       List<String> states = dataProvider.getAllStates();
			
	       modelAndView.addObject("states",states);
		   modelAndView.addObject("cities",cities);
    	   modelAndView.addObject("transporter",transporter);
    	   
    	   return modelAndView;
       }
       
       @RequestMapping("saveTransporterChanges")
       public ModelAndView saveTransporterChanges(@ModelAttribute("transporter") Transporter transporter, @RequestParam("profilePic") MultipartFile profilePicture)
       {
	       	try
	   		{
	   			byte b[] = profilePicture.getBytes();
	   			Blob blob = BlobProxy.generateProxy(b);
	   			transporter.setProfilePicture(blob);
	   		}
	   		catch(IOException e)
	   		{
	   			System.out.println("Error in Transporter Profile Picture : " + e);
	   		}
	       	
	       	transporterDAO.updateTransporter(transporter);
	       	
	       	ModelAndView modelAndView = new ModelAndView("AlertMessage");
  		    modelAndView.addObject("error","Details Updated!!");
  		    modelAndView.addObject("path","transporterHome");
  	       
  		    return modelAndView;
       }
       
       @RequestMapping("viewTransporter")
       public ModelAndView showTransporter(@SessionAttribute("email") String email)
       {
    	   Transporter transporter = transporterDAO.getTransporterByEmail(email);
    	   ModelAndView modelAndView = new ModelAndView("TransporterProfile");
    	   modelAndView.addObject("transporter",transporter);
    	   
    	   return modelAndView;
       }
       
       @RequestMapping("transporterHome")
       public String showTransporterHome()
       {
    	   return "TransporterHome";
       }
}
