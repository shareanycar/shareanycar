package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CustomerDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Booking;
import com.shareanycar.model.Car;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;


public class BookingServiceTest extends HK2Runner{

	@Inject
	public BookingDao bookingDao;

	@Inject
	public CarDao carDao;

	@Inject
	public OwnerDao ownerDao;

	@Inject
	public CustomerDao customerDao;

	@Inject
	public CustomerService customerService;

	@Inject
	public OwnerService ownerService;

	@Inject
	public BookingService bookingService;
	
	@Inject
	public CarService carService;

	@Before
	public void setUp() throws Exception {
		bookingDao.deleteAll();
		carDao.deleteAll();
		customerDao.deleteAll();
		ownerDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void bookCarTest() {
		Long owid;
		try {
			owid = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77",
					"test@test.com", "letmein");
			Owner owner = ownerService.findOwnerById(owid);

			Long id = carService.create(owner.getId(), "brand name", "model name", "BMW", "new car", 2016, null, null);
			Car car = carService.findCarById(id);

			owner = ownerService.findOwnerByEmail("test@test.com");
			Customer customer = customerService.createCustomer("FirstName", "LastName", "7(777)777-77-77", "test@test.com",
					"letmein");

			Booking booking = bookingService.bookCar(customer, car, new Date(), new Date());

			assertNotNull(booking);

			owner = ownerService.findOwnerByEmail("test@test.com");
			assertEquals("1 booking for a car", 1, owner.getCars().iterator().next().getBookings().size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void confirmBookingTest() {
		Long owid;
		try {
			owid = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77",
					"test@test.com", "letmein");
			Owner owner = ownerService.findOwnerById(owid);

			Long id = carService.create(owner.getId(),"brand name", "model name", "BMW", "new car", 2016, null, null);
			Car car = carService.findCarById(id);

			owner = ownerService.findOwnerByEmail("test@test.com");
			Customer customer = customerService.createCustomer("FirstName", "LastName", "7(777)777-77-77", "test@test.com",
					"letmein");
			
			Booking booking = bookingService.bookCar(customer, car, new Date(), new Date());
			
			assertFalse("not confirmed", booking.isConfirmed());
			
			owner = ownerService.findOwnerByEmail("test@test.com");
			customer = customerService.findCustomerByEmail("test@test.com");
			
			booking = bookingService.confirmBooking(owner, customer, owner.getCars().iterator().next());
			assertTrue("booking confirmed", booking.isConfirmed());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void disableBooking(){
		Long owid;
		try {
			owid = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77",
					"test@test.com", "letmein");
			Owner owner = ownerService.findOwnerById(owid);
			
			Long id = carService.create(owner.getId(),"brand name", "model name", "BMW", "new car", 2016, null, null);
			Car car = carService.findCarById(id);

			owner = ownerService.findOwnerByEmail("test@test.com");
			Customer customer = customerService.createCustomer("FirstName", "LastName", "7(777)777-77-77", "test@test.com",
					"letmein");
			
			Booking booking = bookingService.bookCar(customer, car, new Date(), new Date());
			assertTrue("booking is active", booking.isActive());
			
			booking = bookingService.disableBooking(customer, car);
			assertFalse("booking is not active", booking.isActive());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}




