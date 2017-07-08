package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.enums.CarStatus;
import com.shareanycar.model.Car;

@Service
public class CarDao extends BasicDao<Car>{

	public CarDao() {
		super("Car");
	}
	
	@Inject
	public ExtDao<?> extDao;
	
	@SuppressWarnings("unchecked")
	public List<Car> findAvailable(){
		return (List<Car>) extDao.findListByParam("from Car where status = :status", "status", CarStatus.ACTIVE);
	}
	
}
