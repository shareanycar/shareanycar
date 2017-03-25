package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Model;

@Service
public class ModelDao extends BasicDao<Model>{

	public ModelDao() {
		super("Model");
	}
	
	@Inject
	public ExtDao<?> extDao;
	
	public Model findModelByName(String name){
		return (Model) extDao.findOneByParam("from Model where name = :name", "name", name);
	}
	
	public List<Model> findModelByBrandId(Long id){
		return (List<Model>) extDao.findListByParam("from Model where brand_id = :id", "brand_id", id);
	}
	
}
