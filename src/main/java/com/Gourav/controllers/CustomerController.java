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
import com.Gourav.DAO.CustomerDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Customer;

@Controller
@SessionAttributes(names= {"email","realOTP","customer"})
public class CustomerController
{
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private DataProvider dataProvider;
	
	@Autowired
	private CommonDAO commonDAO;

    @RequestMapping("customerSignup")
	public ModelAndView showCustomerSignupForm()
	{
		ModelAndView modelAndView = new ModelAndView("CustomerSignup");
		
		List<String> cities = dataProvider.getAllCities();
    	List<String> states = dataProvider.getAllStates();
		
    	modelAndView.addObject("states",states);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("customer",new Customer());
		
		return modelAndView; 
	}
    
    @RequestMapping("saveCustomer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("profilePic") MultipartFile profilePicture)
    {
    	try
		{
			byte b[] = profilePicture.getBytes();
			Blob blob = BlobProxy.generateProxy(b);
			customer.setProfilePicture(blob);
		}
		catch(IOException e)
		{
			System.out.println("Error in Customer Profile Picture : " + e);
		}
    	
    	int otpArray[] = new int[1];
    	
    	commonDAO.sendMail(customer.getEmail(),otpArray);
    	
    	ModelAndView modelAndView = new ModelAndView("OTPFormCustomer");
    	modelAndView.addObject("email",customer.getEmail());
    	modelAndView.addObject("realOTP",otpArray[0]);
    	modelAndView.addObject("customer",customer);
    	
    	return modelAndView;
    }
    
    @RequestMapping("LoadCustomerDP")
	public void loadCustomerImage(@RequestParam("email") String email, HttpServletResponse response)
	{
		response.setContentType("image/png");
		Customer customer = customerDAO.getCustomerById(email);
		Blob blob = customer.getProfilePicture();
		
		try
		{
			byte b[] = blob.getBytes(1,(int)blob.length());
			ServletOutputStream out = response.getOutputStream();
			out.write(b);
			out.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error in LoadCustomerDP : " + ex);	
		}
	}
    
    @RequestMapping("customerLogin")
    public String showCustomerLoginForm()
    {
 	   return "CustomerLogin";
    }
    
    @RequestMapping("saveCustomerLogin")
    public ModelAndView verifyCustomer(@RequestParam("email") String email,@RequestParam("password") String password)
    {
 	   boolean status = customerDAO.verifyCustomer(email,password);
 	   
 	   if(status)
 	   {
 		   ModelAndView modelAndView = new ModelAndView("CustomerHome");
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
    
    @RequestMapping("viewCustomer")
    public ModelAndView viewCustomer(@SessionAttribute("email") String email)
    {
    	Customer customer = customerDAO.getCustomerById(email);
    	ModelAndView modelAndView = new ModelAndView("CustomerProfile");
    	modelAndView.addObject("customer",customer);
    	
    	return modelAndView;
    }
    
    @RequestMapping("saveCustomerChanges")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("profilePic") MultipartFile profilePicture)
    {
    	try
		{
			byte b[] = profilePicture.getBytes();
			Blob blob = BlobProxy.generateProxy(b);
			customer.setProfilePicture(blob);
		}
		catch(IOException e)
		{
			System.out.println("Error in Customer Profile Picture : " + e);
		}
    	
    	customerDAO.updateCustomer(customer);
    	
    	ModelAndView modelAndView = new ModelAndView("AlertMessage");
 	    modelAndView.addObject("error","Details Updated!!");
 	    modelAndView.addObject("path","customerHome");
 	    return modelAndView;
    }
    
    @RequestMapping("updateCustomer")
    public ModelAndView showCustomerUpdateForm(@SessionAttribute("email") String email)
	{	
		ModelAndView modelAndView = new ModelAndView("CustomerUpdateForm");
		Customer customer = customerDAO.getCustomerById(email);
		
		List<String> cities = dataProvider.getAllCities();
    	List<String> states = dataProvider.getAllStates();
		
    	modelAndView.addObject("states",states);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("customer",customer);
		
		return modelAndView; 
	}
    
    
    @RequestMapping("customerHome")
    public String showCustomerHome()
    {
    	return "CustomerHome";
    }
}
