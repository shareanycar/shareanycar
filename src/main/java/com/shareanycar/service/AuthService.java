package com.shareanycar.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import javax.inject.Inject;

import com.shareanycar.dao.CustomerDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;
import com.shareanycar.util.MiscUtils;

public class AuthService {
	
	@Inject
	public OwnerDao ownerDao;
	
	@Inject
	public CustomerDao customerDao;
	
	@Inject
	public MiscUtils miscUtils;
	
	public Owner authenticateOwner(String token){
		Owner owner = ownerDao.findOwnerByToken(token);
		return owner;
	}
	
	public Customer authenticateCustomer(String token){
		Customer customer = customerDao.findCustomerByToken(token);
		return customer;
	}
	
	public Owner authenticateOwner(String email, String password){
		Owner owner = ownerDao.findOwnerByEmail(email);
		
		if(owner == null) return null;
		
		if(owner.getEmail().equals(email) && owner.getPassword().equals(password)){
			if(owner.getToken() == null){
				owner.setToken(miscUtils.randonString());
				ownerDao.save(owner);
			}
			
			return owner;
		}else{
			return null;
		}
	}
	
	public Customer authenticateCustomer(String email, String password){
		Customer customer = customerDao.findCustomerByEmail(email);
		
		if(customer == null) return null;
		
		if(customer.getEmail().equals(email) && customer.getPassword().equals(password)){
			if(customer.getToken() == null){
				customer.setToken(miscUtils.randonString());
				customerDao.save(customer);
			}
			return customer;
		}else{
			return null;
		}
	}
	
}
