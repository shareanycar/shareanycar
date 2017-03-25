package com.shareanycar.dao;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

import com.shareanycar.model.Brand;

@Service
public class BrandDao extends BasicDao<Brand>{

	public BrandDao() {
		super("Brand");
	}
	@Inject
	Logger logger;
	
	public Brand findBrandByName(String name){
		Session session = SessionUtil.getSession();
		Query<Brand> query = session.createQuery("from Brand where name = :name");
		query.setParameter("name", name);
		Brand brand;
		try{
			brand = (Brand) query.getSingleResult();
		}catch(NoResultException e){
			brand = null;
		}
		session.close();
		return brand;
	}
}
