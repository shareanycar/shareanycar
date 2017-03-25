package com.shareanycar.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Booking;

@Service
public class BookingDao extends BasicDao<Booking>{

	public BookingDao() {
		super("Booking");
	}

	public Booking findBookingByCustomerIdAndCarId(Long customerId, Long carId){
		Session session = SessionUtil.getSession();
		   Query<Booking> query = session.createQuery("from Booking where customer_id = :customerId and car_id = :carId");
		   query.setParameter("customerId", customerId);
		   query.setParameter("carId", carId);
		   Booking booking;
		   try{
			   booking = (Booking) query.getSingleResult();
		   }catch(NoResultException e){
			   booking = null;
		   }
		   session.close();
		   return booking;
	}
	
	public List<Booking> findBookingByCarId(Long carId){
		Session session = SessionUtil.getSession();
		   Query<Booking> query = session.createQuery("from Booking where  car_id = :carId");
		   query.setParameter("carId", carId);
		   List<Booking> bookings = (List<Booking>) query.getResultList();
		   session.close();
		   return bookings;
	}
}
