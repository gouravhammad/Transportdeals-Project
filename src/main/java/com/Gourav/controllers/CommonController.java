package com.Gourav.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.Gourav.DAO.CustomerDAO;
import com.Gourav.DAO.TransporterDAO;
import com.Gourav.models.Customer;
import com.Gourav.models.Transporter;

@Controller
public class CommonController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private TransporterDAO transporterDAO;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping("home")
	public String showHome() {
		return "Home";
	}
	
	@RequestMapping("checkOTPCustomer")
	public ModelAndView verifyOTPCustomer(@RequestParam("OTPCode") int OTPCode, @SessionAttribute("email") String email, @SessionAttribute("realOTP") int realOTP, @SessionAttribute("customer") Customer customer, HttpServletRequest request)
	{ 
		if(OTPCode == realOTP)
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("TRANSPORTDEALS");
			mailMessage.setCc("TransportDeals@gmail.com");
			mailMessage.setSubject("ACCOUNT SUCCESSFULLY CREATED");
			mailMessage.setText("Account Verified of Customer! Thanks for joining us.");
			mailSender.send(mailMessage);
			
			customerDAO.addCustomer(customer);
		    
		    HttpSession session = request.getSession();
		    session.removeAttribute("email");
		    session.removeAttribute("customer");
		    session.removeAttribute("realOTP ");
		    
		    ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Successfully verified!!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
		else
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("TRANSPORTDEALS");
			mailMessage.setCc("TransportDeals@gmail.com");
			mailMessage.setSubject("ACCOUNT NOT VERIFIED");
			mailMessage.setText("Account Not Verified of Customer! Invalid OTP entered.. Please register again.");
			mailSender.send(mailMessage);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Not Verified! Please try again later!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
	}
	
	@RequestMapping("checkOTPTransporter")
	public ModelAndView verifyOTPTransporter(@RequestParam("OTPCode") int OTPCode, @SessionAttribute("email") String email, @SessionAttribute("realOTP") int realOTP, @SessionAttribute("transporter") Transporter transporter, HttpServletRequest request)
	{ 
		if(OTPCode == realOTP)
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("TRANSPORTDEALS");
			mailMessage.setCc("TransportDeals@gmail.com");
			mailMessage.setSubject("ACCOUNT SUCCESSFULLY CREATED");
			mailMessage.setText("Account Verified of Transporter! Thanks for joining us.");
			mailSender.send(mailMessage);
			
			transporterDAO.addTransporter(transporter);
		    
		    HttpSession session = request.getSession();
		    session.removeAttribute("email");
		    session.removeAttribute("transporter");
		    session.removeAttribute("realOTP ");
		    
		    ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Successfully verified!!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
		else
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("TRANSPORTDEALS");
			mailMessage.setCc("TransportDeals@gmail.com");
			mailMessage.setSubject("ACCOUNT NOT VERIFIED");
			mailMessage.setText("Account Not Verified of Transporter! Invalid OTP entered.. Please register again.");
			mailSender.send(mailMessage);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Not Verified! Please try again later!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
	}
	
	@RequestMapping("logoutConfirmation")
	public String logoutConfirm()
    {
	   	return "LogoutConfirmation";
	}

    @RequestMapping("logout")
    public String showHome(HttpServletRequest request,HttpServletResponse response)
    {
    	  HttpSession session = request.getSession();
    	  session.setAttribute("email","null");
          return "Home";
    }
}
