package com.Gourav.DAOImplementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAO.TransporterDAO;
import com.Gourav.models.Transporter;

@Component
public class TransporterDAOImplementation implements TransporterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public void addTransporter(Transporter transporter)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(transporter);
		tr.commit();
		session.close();
	}

	@Override
	public boolean verifyTransporter(String email, String password) 
	{
		
		Session session = sessionFactory.openSession();
		Transporter transporter = session.get(Transporter.class,email);
		session.close();
		
		if(transporter != null)	
		{
			if(transporter.getPassword().contains(password))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Transporter getTransporterByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		Transporter transporter = session.get(Transporter.class,email);
		session.close();

		return transporter;
	}

	@Override
	public void updateTransporter(Transporter transporter) 
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(transporter);
		tr.commit();
		session.close();
	}

}
