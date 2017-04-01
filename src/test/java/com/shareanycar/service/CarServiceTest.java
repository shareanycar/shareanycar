package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;
import com.shareanycar.model.TransmissionType;

public class CarServiceTest extends HK2Runner {

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
	
	private Owner owner;
	private Location loc;
	private Car car1;
	private Car car2;
	private Long owId;
	private TransmissionType transmissionType;
	private FuelType fuelType;
	private CarType carType ;
	
	@Before
	public void setUp() throws Exception {
		bookingDao.deleteAll();
		
		carDao.deleteAll();
		ownerDao.deleteAll();
		
		owner = new Owner.Builder().setEmail("email").build();
		car1 = new Car.Builder().setName("First Car").build();
		car2 = new Car.Builder().setName("Second Car").build();
		carType = new CarType("jeep");
		transmissionType = new TransmissionType("manual");
		fuelType = new FuelType("petrol");

		loc = new Location.Builder().setCountry("Russia").setCity("Moscow").build();
		owId = ownerService.create(owner, loc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createCarTest() {

		try {
			
			Long id1 = carService.create(owId, car1, loc,transmissionType, carType, fuelType);
			Long id2 = carService.create(owId, car2, loc,transmissionType, carType, fuelType);
			
			car1 = carService.findCarById(id1);
			car2 = carService.findCarById(id2);

			assertNotNull("First Car", car1);
			assertNotNull("Second Car", car2);

			assertEquals("First Car", "First Car", car1.getName());
			assertEquals("Second Car", "Second Car", car2.getName());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void updateCarInfoTest() {
		try {		
			Long id1 = carService.create(owId, car1, loc,transmissionType, carType, fuelType);
			car1 = carService.findCarById(id1);

			assertEquals("First Car", "First Car", car1.getName());

			carService.update(owId, id1, car2, loc, transmissionType, carType, fuelType);
			

			car1 = carService.findCarById(id1);

			assertEquals("Second Car", "Second Car", car1.getName());
		} catch (Exception e1) {
			fail(e1.getMessage());
		}

	}

	@Test
	public void removeCarTest() {
		try {
			Long id = carService.create(owId, car1, loc,transmissionType, carType, fuelType);
			Car car = carService.findCarById(id);
			
			assertNotNull("Car created", car);

			carService.delete(owId, id);

		} catch (Exception e1) {
			fail(e1.getMessage());
			
		}

	}

}


