package com.shareanycar.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class BasicDao<T> {
	private String table;
	
	public BasicDao(String table){
		this.table = table;
	}
	
	@SuppressWarnings("unchecked")
	public T save(T bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        T e = (T) session.merge(bean);
        tx.commit();
        session.close();
        
        return e;
    }
	
	public void saveAll(List<T> beans){
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		for(T bean : beans){
			session.merge(bean);
		}
		
		tx.commit();
		session.close();
			
	}
    
    @SuppressWarnings("unchecked")
	public List<T> findAll(){
        Session session = SessionUtil.getSession();    
        Query<T> query = session.createQuery("from " + table);
        List<T> elems =  query.getResultList();
        session.close();
        return elems;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(List<Long> ids){
    	List<T> list = new ArrayList<>();
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from " + table + " where id = :id");
        for(Long i : ids){
        	 query.setParameter("id", i);
        	 T e = (T) query.getSingleResult();
        	 list.add(e);
        }
        session.close();
        return list;
    }
    
    public void delete(T bean) {
    	
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(bean);
 
        tx.commit();
        session.close(); 
    }
    
   @SuppressWarnings({ "unchecked", "rawtypes" })
public T findOne(Long id){
	   Session session = SessionUtil.getSession();
	   Query query = session.createQuery("from " + table + " where id = :id");
	   query.setParameter("id", id);
	   T e = (T) query.getSingleResult();
	   session.close();
	   return e;
   }
   
   @SuppressWarnings("rawtypes")
public void deleteAll(){
	   Session session = SessionUtil.getSession();
	   Transaction tx = session.beginTransaction();
	   Query query = session.createQuery("delete from " + table );
	   query.executeUpdate();
	   tx.commit();
       session.close(); 
   }
}
