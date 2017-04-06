package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

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
	
	public List<Car> findCarByUserId(Long id){
		return (List<Car>) extDao.findListByParam("from Car where user_id = :userId", "user_id", id);
	}
	
}
