package com.shareanycar.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Location;

@Service
public class LocationDao extends BasicDao<Location> {

	public LocationDao() {
		super("Location");
	}

	public Location findByCountryAndCity(String country, String city){
		Session session = SessionUtil.getSession();
		Query<Location> query = session.createQuery("from Location where country = :country and city = :city");
		query.setParameter("country", country);
		query.setParameter("city", city);
		Location location;
		try{
			location = (Location) query.getSingleResult();
		}catch(NoResultException e){
			location = null;
		}
		session.close();
		return location;
	}

	public List<Location> findByCountry(String country) {
		Session session = SessionUtil.getSession();
		Query<Location> query = session.createQuery("from Location where country = :country");
		query.setParameter("country", country);
		List<Location> locations = (List<Location>) query.getResultList();
		session.close();
		return locations;
	}

	public List<Location> findByCity(String city) {
		Session session = SessionUtil.getSession();
		Query<Location> query = session.createQuery("from Location where city = :city");
		query.setParameter("city", city);
		List<Location> locations = (List<Location>) query.getResultList();
		session.close();
		return locations;
	}

}
