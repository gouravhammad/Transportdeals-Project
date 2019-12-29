package com.Gourav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;import com.Gourav.DAO.AdminDAO;
import com.Gourav.models.Admin;

@Controller
@SessionAttributes(names= {"email"})
public class AdminController
{	
	@Autowired
	private AdminDAO adminDAO;

    @RequestMapping("adminLogin")
	public ModelAndView showAdminForm()
	{	

		ModelAndView modelAndView = new ModelAndView("AdminLogin");
		modelAndView.addObject("admin",new Admin());
		
		return modelAndView; 
	}
    
    @RequestMapping("saveAdmin")
	public ModelAndView verifyAdmin(@ModelAttribute("admin") Admin admin)
	{
    	boolean status = adminDAO.verifyAdmin(admin);
    	
    	if(status)
    	{
    		ModelAndView modelAndView = new ModelAndView("AdminHome");
    		modelAndView.addObject("email",admin.getEmail());
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
}
