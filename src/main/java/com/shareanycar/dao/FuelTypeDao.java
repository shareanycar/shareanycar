package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.FuelType;

@Service
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
