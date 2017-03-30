package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.shareanycar.model.Owner;

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

		try {
			Long id;
			id = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com",
					"letmein");
			Owner owner = ownerService.findOwnerById(id);
			Long id1 = carService.create(owner.getId(), "toyota camry",
					"one of best cars ever", 1999, "mechanic","sedan",6, "USA", "New York");
			Car car1 = carService.findCarById(id1);
			Long id2 = carService.create(owner.getId(), "mercedes", "another great car",
					2005, "mechanic", "sedan", 6, "USA", "New York");
			Car car2 = carService.findCarById(id2);

			assertNotNull("First car", car1);
			assertNotNull("Second car", car2);

			assertEquals("toyota created", "toyota camry", car1.getName());
			assertEquals("mercedes created", "mercedes", car2.getName());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void updateCarInfoTest() {
		Long owid;
		try {
			owid = ownerService.create("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77",
					"test@test1.com", "letmein");
			Owner owner = ownerService.findOwnerById(owid);

			Long id = carService.create(owner.getId(), "BMW", "DESC", 2011,"mechanic", "sedan", 6, "USA",
					"Chicago");
			Car car = carService.findCarById(id);

			assertEquals("car name BMW", "BMW", car.getName());
			assertEquals("car desc DESC", "DESC", car.getDescription());

			owner = ownerService.findOwnerByEmail("test@test1.com");
			
			carService.update(owner.getId(), car.getId(),  "BMWUPD", "DESCUPD", 2011, "mechanic", "sedan", 6, "USA",
					"Chicago");

			car = carService.findCarById(car.getId());

			assertEquals("car BMW updated", "BMWUPD", car.getName());
			assertEquals("car DESC updated", "DESCUPD", car.getDescription());
		} catch (Exception e1) {
			fail(e1.getMessage());
		}

	}

	@Test
	public void removeCarTest() {
		Long owid;
		try {
			owid = ownerService.create("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77",
					"test@test1.com", "letmein");
			Owner owner = ownerService.findOwnerById(owid);

			Long id = carService.create(owner.getId(),  "BMW", "DESC", 2011, "mechanic", "sedan",6 , "USA",
					"Chicago");
			Car car = carService.findCarById(id);

			owner = ownerService.findOwnerByEmail("test@test1.com");

			assertNotNull("Owner created", owner);
			assertNotNull("Car created", car);

			carService.delete(owner.getId(), car.getId());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}

	}

}
