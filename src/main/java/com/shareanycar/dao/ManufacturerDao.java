package com.shareanycar.dao;

import javax.inject.Inject;

import com.shareanycar.model.Manufacturer;

public class ManufacturerDao extends BasicDao<Manufacturer>{

	@Inject
	public ExtDao<?> extDao;
	
	public ManufacturerDao() {
		super("Manufacturer");
	}
	
	public Manufacturer findByName(String name){
		return (Manufacturer) extDao.findOneByParam("from Manufacturer where name = :name", "name", name);
	}
	

}
