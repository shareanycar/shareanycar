package com.shareanycar.dao;

import javax.inject.Inject;

import com.shareanycar.model.CarType;

public class CarTypeDao extends BasicDao<CarType>{

	@Inject
	public ExtDao<?> extDao;
	
	public CarTypeDao() {
		super("CarType");
	}

	public CarType findByName(String name){
		return (CarType) extDao.findOneByParam("from CarType where name = :name", "name", name);
	}
}
