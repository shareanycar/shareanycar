package com.shareanycar.dao;

import javax.inject.Inject;

import com.shareanycar.model.FuelType;

public class FuelTypeDao extends BasicDao<FuelType>{

	@Inject
	public ExtDao<?> extDao;
	
	public FuelTypeDao() {
		super("FuelType");
	}

	public FuelType findByName(String name){
		return (FuelType) extDao.findOneByParam("from FuelType where name = :name", "name", name);
	}
}
