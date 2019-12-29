package com.Gourav.DAOImplementation;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAO.BookingDAO;
import com.Gourav.models.Booking;

@Component
public class BookingDAOImplementation implements BookingDAO{


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addDeal(Booking booking)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(booking);
		
		tr.commit();
		session.close();
	}

	@Override
	public List<Booking> getAllDealsByRegNo(String email) 
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Booking.class);
		Query query = session.createQuery("from Booking where vehicle.transporter.email=:email");
		query.setParameter("email", email);
		List<Booking> bookings = query.list();
		session.close();
		
		return bookings;
	}

	@Override
	public void updateDeal(Booking booking) 
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(booking);
		
		tr.commit();
		session.close();
	}

	@Override
	public void deleteDeal(int bookingCode)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Booking booking = session.get(Booking.class,bookingCode);
		
		session.remove(booking);
		
		tr.commit();
		session.close();
	}

	@Override
	public Booking getBookingById(int bookingCode)
	{
		Session session = sessionFactory.openSession();
		Booking booking = session.get(Booking.class,bookingCode);
		session.close();
		
		return booking;
	}

	@Override
	public List<Booking> getAllDeals()
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Booking.class);
		List<Booking> bookings = criteria.list();
		session.close();
		
		return bookings;
	}

	@Override
	public List<Booking> getAllDealsByCityRange(String sourceCity, String destinationCity)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Booking.class);
		Query query = session.createQuery("from Booking where sourceCity=:sourceCity AND destinationCity=:destinationCity");
		query.setParameter("sourceCity",sourceCity);
		query.setParameter("destinationCity",destinationCity);
		List<Booking> bookings = query.list();
		session.close();
		
		return bookings;
	}
	
	@Override
	public List<Booking> getAllDealsByDate(Date date)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Booking.class);
		Query query = session.createQuery("from Booking where date=:date");
		query.setParameter("date",date);
		List<Booking> bookings = query.list();
		session.close();
		
		return bookings;
	}
	
	@Override
	public List<Booking> getAllDealsByDateRange(Date startDate, Date endDate)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Booking.class);
		Query query = session.createQuery("from Booking where date>=:startDate AND date<=:endDate");
		query.setParameter("startDate",startDate);
		query.setParameter("endDate",endDate);
		List<Booking> bookings = query.list();
		session.close();
		
		return bookings;
	}
}
