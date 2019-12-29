package com.Gourav.DAOImplementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.Gourav.DAO.CommonDAO;
import com.Gourav.models.OTP;


@Component
public class CommonDAOImplementation implements CommonDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void sendMail(String email,int otpArray[])
	{
		int otp = getOTP();
		
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("OTP VERIFICATION");
		mailMessage.setText("Your OTP Code is : "+ otp + "\nEnter this code to verify your account! \nDo not share this with anyone else!!!");
		mailSender.send(mailMessage);
		
		otpArray[0] = otp;
	}
	
	@Override
	public int getOTP()
	{
		Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(OTP.class);
	    
	    List<OTP> otp = criteria.list();
	    
	    int otpNumber = 0;
	    
	    for(OTP o:otp)
	    {
	    	otpNumber = o.getOTPNumber();
	    }
	     
	    Transaction tr = session.beginTransaction();
	    String query = "update OTP set OTPNumber = OTPNumber + 1";
	    Query hql = session.createQuery(query);
	    
	    try
	    {
	    	hql.executeUpdate();
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("OTP Fetching Error : " + ex);
	    }
	    
	    tr.commit();
	    session.close();
	    
		return otpNumber;
	}
		
}
