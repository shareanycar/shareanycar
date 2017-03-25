package com.shareanycar.dao;


import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.config.CustomResourceConfig;
import com.shareanycar.model.Car;


public class CarDaoTest extends HK2Runner{
	
	@Inject
	public CarDao carDao ;
	
	CustomResourceConfig config;

	@Before
	public void setUp() throws Exception {
		config = new CustomResourceConfig();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void carCreate() {
		Car car ;
		Car car1 = new Car();
		Car car2 = new Car();
		
		car1.setName("toyota");
		car1.setDescription("very good car");
		
		car2.setName("bmw");
		car2.setDescription("brand new");
		
		
		carDao.save(car1);
		carDao.save(car2);
				
		car = carDao.findOne(1L);
		assertEquals("Equal names ",  "toyota", car.getName());
		
		car = carDao.findOne(2L);
		assertEquals("Equal names ",  "bmw", car.getName());
		
	}

}


