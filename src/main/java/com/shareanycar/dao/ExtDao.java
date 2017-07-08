package com.shareanycar.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

@Service
public class ExtDao<T> {
	@SuppressWarnings("unchecked")
	public T findOneByParam(String sql, String param, Object value){
		Session session = SessionUtil.getSession();
		Query<T> query = session.createQuery(sql);
		query.setParameter(param, value);
		T elem ;
		try {
			elem = (T) query.getSingleResult();
		} catch (NoResultException e) {
			elem = null;
		}
		session.close();
		return elem;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findListByParam(String sql, String param, Object value){
		Session session = SessionUtil.getSession();
		Query<T> query = session.createQuery(sql);
		query.setParameter(param, value);
		List<T> elems = (List<T>) query.getResultList();
		session.close();
		return elems;
	}
	
	@SuppressWarnings("unchecked")
	public T findOneByParams(String sql, String[] params, Object[] values) {
		if(params.length != values.length){
			throw new IllegalArgumentException("length of params does not match length of values");
		}
		
		Session session = SessionUtil.getSession();
		Query<T> query = session.createQuery(sql);
		
		for(int i=0;i<params.length;i++){
			query.setParameter(params[i], values[i]);
		}
		
		T elem ;
		try {
			elem = (T) query.getSingleResult();
		} catch (NoResultException e) {
			elem = null;
		}
		session.close();
		
		return elem;	
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findListByParams(String sql, String[] params, Object[] values) {
		
		if(params.length != values.length){
			throw new IllegalArgumentException("length of params does not match length of values");
		}
		
		Session session = SessionUtil.getSession();
		Query<T> query = session.createQuery(sql);
		for(int i=0; i<params.length;i++){
			query.setParameter(params[i], values[i]);
		}
		List<T> elems = (List<T>) query.getResultList();
		session.close();
		return elems;
	}
}
