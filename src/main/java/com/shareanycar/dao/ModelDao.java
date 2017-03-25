package com.shareanycar.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Location;
import com.shareanycar.model.Model;

@Service
public class ModelDao extends BasicDao<Model>{

	public ModelDao() {
		super("Model");
	}
	
	public Model findModelByName(String name){
		Session session = SessionUtil.getSession();
		Query<Model> query = session.createQuery("from Model where name = :name");
		query.setParameter("name", name);
		Model model;
		try{
			model = (Model) query.getSingleResult();
		}catch(Exception e){
			model = null;
		}
		session.close();
		return model;
	}
	
	public List<Model> findModelByBrandId(Long id){
		Session session = SessionUtil.getSession();
		Query<Model> query = session.createQuery("from Model where brand_id = :id");
		query.setParameter("id", id);
		List<Model> models = (List<Model>) query.getResultList();
		session.close();
		return models;
	}
}
