package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Brand;

@Service
public class BrandDao extends BasicDao<Brand>{

	public BrandDao() {
		super("Brand");
	}
	
	
	@Inject
	public ExtDao<?> extDao;
	
	public Brand findBrandByName(String name){
		return (Brand) extDao.findOneByParam("from Brand where name = :name", "name", name);
	}
	
	
}
