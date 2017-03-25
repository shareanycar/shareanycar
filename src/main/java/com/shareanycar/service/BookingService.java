package com.shareanycar.service;

import java.util.Date;

import javax.inject.Inject;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.model.Booking;
import com.shareanycar.model.Car;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;

public class BookingService {

	@Inject
	public BookingDao bookingDao;

	public Booking bookCar(Customer customer, Car car, Date dateStart, Date dateEnd) {
		Booking booking = bookingDao.findBookingByCustomerIdAndCarId(customer.getId(), car.getId());

		if (booking == null) {
			booking = new Booking();
			booking.setCar(car);
			booking.setCustomer(customer);
		}

		booking.setDateStart(dateStart);
		booking.setDateEnd(dateEnd);
		booking.setActive(true);
		booking.setConfirmed(false);

		bookingDao.save(booking);
		return booking;
	}

	public Booking confirmBooking(Owner owner, Customer customer, Car car) {
		Booking booking = bookingDao.findBookingByCustomerIdAndCarId(customer.getId(), car.getId());
		if(booking != null){
			if(booking.isActive()){
				for(Car c : owner.getCars()){
					if(c.getId() == car.getId()){
						booking.setConfirmed(true);
						bookingDao.save(booking);
						return booking;
					}
				}
			}
		}
		
		return null;
	}
	
	public Booking disableBooking(Customer customer, Car car){
		Booking booking = bookingDao.findBookingByCustomerIdAndCarId(customer.getId(), car.getId());
		if(booking != null){
			booking.setActive(false);
			bookingDao.save(booking);
			return booking;
		}
		return null;
	}

}
