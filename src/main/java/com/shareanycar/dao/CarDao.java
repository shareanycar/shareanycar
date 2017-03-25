package com.shareanycar.dao;

import java.util.List;

import org.glassfish.jersey.spi.Contract;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Car;

@Service
public class CarDao extends BasicDao<Car>{

	public CarDao() {
		super("Car");
	}

	public List<Car> findCarByLocationId(Long locationId){
		Session session = SessionUtil.getSession();
		Query<Car> query = session.createQuery("from Car where location_id = :locationId");
		query.setParameter("locationId", locationId);
		List<Car> cars = (List<Car>) query.getResultList();
		session.close();
		return cars;
	}
	
	public List<Car> findCarByOwnerId(Long ownerId){
		Session session = SessionUtil.getSession();
		Query<Car> query = session.createQuery("from Car where owner_id = :ownerId");
		query.setParameter("ownerId", ownerId);
		List<Car> cars = (List<Car>) query.getResultList();
		session.close();
		return cars;
	}
}
