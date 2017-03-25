package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Owner;


public class CarServiceTest extends HK2Runner{

	@Inject
	public OwnerDao ownerDao;
	
	@Inject
	public CarDao carDao;
	
	@Inject
	public BookingDao bookingDao;
	
	@Inject
	public OwnerService ownerService;
	
	@Inject
	public CarService carService;

	@Before
	public void setUp() throws Exception {
		bookingDao.deleteAll();
		carDao.deleteAll();
		ownerDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createCarTest() {
		Owner owner = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com", "letmein");

		Car car1 = carService.create(owner.getId(),"brand name", "model name", "toyota camry", "one of best cars ever", 1999, "USA","New York");
		Car car2 = carService.create(owner.getId(),"brand name", "model name", "mercedes", "another great car", 2005,"USA", "New York");

		
		assertNotNull("First car" , car1);
		assertNotNull("Second car" , car2);
		
		assertEquals("toyota created", "toyota camry", car1.getName());
		assertEquals("mercedes created", "mercedes", car2.getName());

	}

	@Test
	public void updateCarInfoTest(){
		Owner owner = ownerService.create("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77", "test@test1.com", "letmein");
		
		Car car = carService.create(owner.getId(),"brand name","model name", "BMW", "DESC", 2011 , "USA", "Chicago");
		
		assertEquals("car name BMW", "BMW", car.getName());
		assertEquals("car desc DESC", "DESC", car.getDescription());
		
		owner = ownerService.findOwnerByEmail("test@test1.com");
		
		car = carService.update(owner.getId(),car.getId(), "model name", "brand name", "BMWUPD", "DESCUPD",2011, "USA", "Chicago");
		
		assertEquals("car BMW updated", "BMWUPD", car.getName());
		assertEquals("car DESC updated", "DESCUPD", car.getDescription());
		
	}
	
	@Test
	public void removeCarTest(){
		Owner owner = ownerService.create("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77", "test@test1.com", "letmein");
		
		Car car = carService.create(owner.getId(),"brand name", "model name", "BMW", "DESC", 2011 , "USA", "Chicago");
		owner = ownerService.findOwnerByEmail("test@test1.com");
		 
		assertNotNull("Owner created", owner);
		assertNotNull("Car created", car);
		
		assertTrue ("car removed", carService.delete(owner.getId(), car.getId()));
		
	}
	
	
}
