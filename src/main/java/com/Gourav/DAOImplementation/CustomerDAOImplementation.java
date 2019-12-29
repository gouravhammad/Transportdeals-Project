package com.Gourav.DAOImplementation;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAO.CustomerDAO;
import com.Gourav.models.Customer;

@Component
public class CustomerDAOImplementation implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public void addCustomer(Customer customer)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(customer);
		tr.commit();
		session.close();
	}

	@Override
	public boolean verifyCustomer(String email, String password) 
	{
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class,email);
		session.close();
		
		if(customer != null)	
		{
			if(customer.getPassword().contains(password))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Customer getCustomerById(String email)
	{
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class,email);
		session.close();
		
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(customer);
		tr.commit();
		session.close();
	}
}
