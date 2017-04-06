package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.UserDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Insurer;
import com.shareanycar.model.Location;
import com.shareanycar.model.Manufacturer;
import com.shareanycar.model.TransmissionType;
import com.shareanycar.model.User;

public class CarServiceTest extends HK2Runner {

	@Inject
	public UserDao userDao;

	@Inject
	public CarDao carDao;

	

	@Inject
	public UserService userService;

	@Inject
	public CarService carService;
	
	private Location loc;
	private Car car1;
	private Car car2;
	private Long owId;
	private TransmissionType transmissionType;
	private FuelType fuelType;
	private CarType carType ;
	private Manufacturer manufacturer;
	private Insurer insurer;
	private User user;
	
	@Before
	public void setUp() throws Exception {
	
		
		carDao.deleteAll();
		userDao.deleteAll();
		
		user = new User("First Name", "Last Name", "test@test.com", "letmein");
		car1 = new Car();
		car1.setLicensePlateNumber("abc");
		car2 = new Car();
		car2.setLicensePlateNumber("bcd");
		carType = new CarType("jeep");
		transmissionType = new TransmissionType("manual");
		fuelType = new FuelType("petrol");
		
		insurer = new Insurer("Alpha");
		manufacturer = new Manufacturer("BMW");

		loc = new Location("Russia", "Moscow");
		owId = userService.create(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createCarTest() {

		try {
			
			Long id1 = carService.create(owId, car1, loc,transmissionType, carType, fuelType,manufacturer,insurer);
			Long id2 = carService.create(owId, car2, loc,transmissionType, carType, fuelType,manufacturer,insurer);
			
			car1 = carService.findCarById(id1);
			car2 = carService.findCarById(id2);

			assertNotNull("First Car", car1);
			assertNotNull("Second Car", car2);

			assertEquals("First Car", "abc", car1.getLicensePlateNumber());
			assertEquals("Second Car", "bcd", car2.getLicensePlateNumber());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void updateCarInfoTest() {
		try {		
			Long id1 = carService.create(owId, car1, loc,transmissionType, carType, fuelType,manufacturer,insurer);
			car1 = carService.findCarById(id1);

			assertEquals("First Car", "abc", car1.getLicensePlateNumber());

			carService.update(owId, id1, car2, loc, transmissionType, carType, fuelType,manufacturer,insurer);
			

			car1 = carService.findCarById(id1);

			assertEquals("Second Car", "bcd", car1.getLicensePlateNumber());
		} catch (Exception e1) {
			fail(e1.getMessage());
		}

	}

	@Test
	public void removeCarTest() {
		try {
			Long id = carService.create(owId, car1, loc,transmissionType, carType, fuelType,manufacturer,insurer);
			Car car = carService.findCarById(id);
			
			assertNotNull("Car created", car);

			carService.delete(owId, id);

		} catch (Exception e1) {
			fail(e1.getMessage());
			
		}

	}

}


