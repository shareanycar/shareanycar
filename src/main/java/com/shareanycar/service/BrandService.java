package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.BrandDao;
import com.shareanycar.model.Brand;

@Service
public class BrandService {
	
	@Inject
	public BrandDao brandDao;

	public Brand prepare(String name){
		name = name.trim().toUpperCase().replaceAll("\\s+", " ");

		Brand brand = brandDao.findBrandByName(name);
		if(brand == null){
			brand = new Brand();
			brand.setName(name);
			brand = brandDao.save(brand);
		}
		return brand;
	}
	
	public List<Brand> findAll(){
		return brandDao.findAll();
	}
	
	public  Brand findBrandById(Long id){
		Brand brand = brandDao.findOne(id);
		return brand;
	}
}
