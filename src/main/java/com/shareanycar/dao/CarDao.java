package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Car;

@Service
public class CarDao extends BasicDao<Car>{

	public CarDao() {
		super("Car");
	}
	
	@Inject
	public ExtDao<?> extDao;
	
	public List<Car> findCarByLocationId(Long id){
		return (List<Car>) extDao.findListByParam("from Car where location_id = :locationId", "location_id", id);
	}
	
	public List<Car> findCarByOwnerId(Long id){
		return (List<Car>) extDao.findListByParam("from Car where owner_id = :ownerId", "owner_id", id);
	}
	
}
