package com.Gourav.DAOImplementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAO.VehicleDAO;
import com.Gourav.models.Vehicle;

@Component
public class VehicleDAOImplementation implements VehicleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addVehicle(Vehicle vehicle)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(vehicle);
		
		tr.commit();
		session.close();
	}

	@Override
	public void removeVehicle(String regNo)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Vehicle vehicle = session.get(Vehicle.class,regNo);
		
		session.remove(vehicle);
		
		tr.commit();
		session.close();
	}

	@Override
	public void updateVehicle(Vehicle vehicle)
	{	
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(vehicle);
		
		tr.commit();
		session.close();
	}

	@Override
	public List<Vehicle> getAllVehicleByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vehicle.class);
		Criterion crt = Restrictions.eq("transporter.email", email);
		criteria.add(crt);
		List<Vehicle> vehicles = criteria.list();
		
		session.close();
		return vehicles;
	}

	@Override
	public Vehicle getVehicleByRegNo(String regNo)
	{
		Session session = sessionFactory.openSession();
		Vehicle vehicle = session.get(Vehicle.class,regNo);
		session.close();
		
		return vehicle;
	}

	@Override
	public List<String> getAllRegNoByEmail(String email)
	{
		List<Vehicle> vehicle = getAllVehicleByEmail(email);
		List<String> regNos = new ArrayList<String>();
		
		for(Vehicle v:vehicle)
		{
			regNos.add(v.getRegNo());
		}
		
		return regNos;
	}
}
