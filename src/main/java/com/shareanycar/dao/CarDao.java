package com.shareanycar.dao;

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
	
	
	
}
