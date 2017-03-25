package com.shareanycar.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;

@Service
public class CustomerDao extends BasicDao<Customer>{

	public CustomerDao() {
		super("Customer");
	}

	public Customer findCustomerByEmail(String email){
		Session session = SessionUtil.getSession();
		   Query<Customer> query = session.createQuery("from Customer where email = :email");
		   query.setParameter("email", email);
		   Customer customer;
		   try{
			   customer = (Customer) query.getSingleResult();
		   }catch(NoResultException e){
			   customer = null;
		   }
		   session.close();
		   return customer;
	}
	
	public Customer findCustomerByToken(String token){
		  Session session = SessionUtil.getSession();
		   Query<Customer> query = session.createQuery("from Customer where  = :token");
		   query.setParameter("token", token);
		   Customer customer;
		   try{
			   customer = (Customer) query.getSingleResult();
		   }catch(NoResultException e){
			   customer = null;
		   }
		   session.close();
		   return customer;
	 }
}
