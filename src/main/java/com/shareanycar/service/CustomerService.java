package com.shareanycar.service;

import java.util.Date;

import javax.inject.Inject;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CommentDao;
import com.shareanycar.dao.CustomerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Comment;
import com.shareanycar.model.Customer;


public class CustomerService {
	@Inject
	public CustomerDao customerDao;
	
	@Inject
	public CarDao carDao;
	
	@Inject
	public CommentDao commentDao;
	
	
	public Customer createCustomer(String firstName, String lastName, String phone, String email, String password){
		Customer customer = customerDao.findCustomerByEmail(email);
		if(customer != null){
			throw new IllegalArgumentException("customer witch such email already exists");
		}
		
		customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer = customerDao.save(customer);
		
		return customer;
	}
	
	public Comment postComment(Long customerId, Long carId, String text){
		Customer customer = customerDao.findOne(customerId);
		Car car = carDao.findOne(carId);
		Comment comment = commentDao.findCommentByCustomerIdAndCarId(customerId, carId);
		
		if(customer == null || car == null){
			throw new IllegalArgumentException("can not customer with id:" + customerId + " or car with id:" +carId);
		}
		
		if(comment != null){
			comment.setText(text);
			comment.setCommentDate(new Date());
		}else{
			comment = new Comment();
			comment.setCar(car);
			comment.setCustomer(customer);
			comment.setCommentDate(new Date());
			comment.setText(text);
		}
		
		comment = commentDao.save(comment);
		return comment;
		
	}
	
	public boolean removeComment(Long customerId, Long carId){
		Customer customer = customerDao.findOne(customerId);
		Car car = carDao.findOne(carId);
		Comment comment = commentDao.findCommentByCustomerIdAndCarId(customer.getId(), car.getId());
		
		if(customer == null || car == null){
			throw new IllegalArgumentException("can not customer with id:" + customerId + " or car with id:" +carId);
		}
		
		if(comment != null){
			commentDao.delete(comment);
			return true;
		}
		return false;
	}
	
	public Customer findCustomerByEmail(String email){
		return customerDao.findCustomerByEmail(email);
	}
	
	public Customer find(Long id){
		return customerDao.findOne(id);
	}
}
