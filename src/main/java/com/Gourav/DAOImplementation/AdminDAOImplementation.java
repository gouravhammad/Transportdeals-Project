package com.Gourav.DAOImplementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAO.AdminDAO;
import com.Gourav.models.Admin;

@Component
public class AdminDAOImplementation implements AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean verifyAdmin(Admin admin) {
		
		Session session = sessionFactory.openSession();
		Admin adminFetched = session.get(Admin.class,admin.getEmail());
		session.close();
		
		if(adminFetched != null)
		{
			if(adminFetched.getPassword().contentEquals(admin.getPassword()))
			{
				return true;
			}
		}
		
		return false;
	}

}
