package com.shareanycar.dao;

import javax.inject.Inject;

import com.shareanycar.model.Insurer;

public class InsurerDao extends BasicDao<Insurer>{

	@Inject
	public ExtDao<?> extDao;
	
	public InsurerDao() {
		super("Insurer");
	}
	
	public Insurer findByName(String name){
		return (Insurer) extDao.findOneByParam("from Insurer where name=:name", "name", name);
	}

}
