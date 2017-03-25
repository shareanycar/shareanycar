package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Customer;

@Service
public class CustomerDao extends BasicDao<Customer>{

	public CustomerDao() {
		super("Customer");
	}

	@Inject
	public ExtDao<?> extDao;
	
	public Customer findCustomerByEmail(String email){
		return (Customer) extDao.findOneByParam("from Customer where email = :email", "email", email);
	}
	
	public Customer findCustomerByToken(String token){
		return (Customer) extDao.findOneByParam("from Customer where  = :token", "token", token);
	}
	
	
	
}
