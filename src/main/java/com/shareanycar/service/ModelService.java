package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.ModelDao;
import com.shareanycar.model.Brand;
import com.shareanycar.model.Model;

@Service
public class ModelService {
	
	
	@Inject
	public ModelDao modelDao;
	
	public Model prepare(String name, Brand brand ){
		name = name.trim().toUpperCase().replaceAll("\\s+", " ");
		
		Model model = modelDao.findModelByName(name);
		if(model == null){
			model = new Model();
			model.setName(name);
			model.setBrand(brand);
			model = modelDao.save(model);
		}
		
		return model;
	}
	
	public List<Model> findModelByBrandId(Long id){
		List<Model> models = modelDao.findModelByBrandId(id);
		return models;
	}
	
}
