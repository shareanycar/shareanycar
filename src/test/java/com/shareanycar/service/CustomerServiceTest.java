package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;
import org.slf4j.Logger;

import com.shareanycar.dao.BrandDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CommentDao;
import com.shareanycar.dao.CustomerDao;
import com.shareanycar.dao.ModelDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;


public class CustomerServiceTest extends HK2Runner{

	@Inject
	public BrandDao brandDao;
	
	@Inject
	public ModelDao modelDao;
	
	@Inject
	public CustomerDao customerDao;
	
	@Inject
	public CommentDao commentDao;
	
	@Inject
	public CarDao carDao;
	
	@Inject
	public OwnerDao ownerDao;
	
	@Inject
	public CustomerService customerService;
	
	@Inject
	public OwnerService ownerService;
	
	@Inject
	public CarService carService;
	
	@Before
	public void setUp() throws Exception {
		
		commentDao.deleteAll();
		carDao.deleteAll();
		ownerDao.deleteAll();
		customerDao.deleteAll();
		modelDao.deleteAll();
		brandDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createCustomerTest() {
		customerService.createCustomer("FirstName", "LastName", "7(777)777-77-77", "test@test.com", "letmein");
		Customer customer = customerService.findCustomerByEmail("test@test.com");
		
		assertEquals("customer should be created", "FirstName", customer.getFirstName());
		
		try{
			customerService.createCustomer("FirstName", "LastName", "7(777)777-77-77", "test@test.com", "letmein");
			fail("customer with the same email already exists");
		}catch(Exception e){
		}
	}
	
	@Test
	public void postCommentOnCar(){
		Long id;
		try {
			id = ownerService.create("Owner", "Owner", "USA", "Chicago", "7(777)777-77-77", "test@test.com", "letmein");
			Owner owner = ownerService.findOwnerById(id);
			
			carService.create(id,"brand name","model name", "BMW", "brand new", 2017, "USA", "Chicago");
			owner = ownerService.findOwnerByEmail("test@test.com");
					
			Set<Car> cars = owner.getCars();
		
			assertEquals("should be 1 car", 1, cars.size());
			
			Car car = cars.iterator().next();
			
			customerService.createCustomer("Customer", "Customer", "7(777)777-77-77", "customer1@test.com", "letmein");
			customerService.createCustomer("Customer", "Customer", "7(777)777-77-77", "customer2@test.com", "letmein");
			
			Customer customer1 = customerService.findCustomerByEmail("customer1@test.com");
			Customer customer2 = customerService.findCustomerByEmail("customer2@test.com");
			
			customerService.postComment(customer1.getId(), car.getId(), "not so good car1");
			customerService.postComment(customer2.getId(), car.getId(), "not so good car2");

			owner = ownerService.findOwnerByEmail("test@test.com");

			assertEquals("2 comments on the car", 2, owner.getCars().iterator().next().getComments().size());
			
			customer1 = customerService.findCustomerByEmail("customer1@test.com");
			
			assertEquals("1 comments from customer 1", 1, customer1.getComments().size());
		} catch (Exception e) {
			fail(e.getMessage());
		} 
		
			
	}
	
	@Test
	public void removeComment(){
		Long id;
		try {
			id = ownerService.create("Owner", "Owner", "USA", "Chicago", "7(777)777-77-77", "test@test.com", "letmein");
			Owner owner = ownerService.findOwnerById(id);
			
			Long carId = carService.create(owner.getId(),"brand name", "model name", "BMW", "brand new", 2017, "USA", "Chicago");
			
			Car car = carService.findCarById(carId);
			
			customerService.createCustomer("Customer", "Customer", "7(777)777-77-77", "test@test.com", "letmein");
			Customer customer = customerService.findCustomerByEmail("test@test.com");
			
			customerService.postComment(customer.getId(), car.getId(), "nice car");
			
			customer = customerService.findCustomerByEmail("test@test.com");
			
			assertEquals("comment created", "nice car", customer.getComments().iterator().next().getText());
			
			customerService.removeComment(customer.getId(), car.getId());
			
			customer = customerService.findCustomerByEmail("test@test.com");
			
			assertFalse("comment removed", customer.getComments().iterator().hasNext());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		

	}

}


