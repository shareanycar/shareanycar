package com.shareanycar.service;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.model.Booking;
import com.shareanycar.model.Car;
import com.shareanycar.model.User;

@Service
public class BookingService {
	@Inject
	public BookingDao bookingDao;

	@Inject
	public CarService carService;

	public void book(Booking booking, User user, Long carId) throws Exception {
		if (booking.getDateFrom().compareTo(booking.getDateTo()) > 0) {
			throw new Exception("incorrent dateFrom and dateTo dates");
		}

		Car car = carService.findById(carId);

		if (car == null) {
			throw new Exception("can not find such car:" + carId);
		}

		booking.setUser(user);
		booking.setCar(car);

		booking = bookingDao.save(booking);
	}

	public List<Booking> carBookings(Long carId, User user) throws Exception {
		Car car = carService.findById(carId);
		if (car == null) {
			throw new Exception("can not find such car:" + carId);
		}

		if (car.getUser().getId() != user.getId()) {
			throw new Exception("car does not belong to current user:" + user.getId());
		}

		LinkedList<Booking> bookings = new LinkedList<>(car.getBookings());

		return bookings;
	}

	public List<Booking> ownerBookings(User user) {
		List<Booking> bookings = new LinkedList<>();

		for (Car c : user.getCars()) {
			bookings.addAll(c.getBookings());
		}

		return bookings;
	}

	public List<Booking> clientBookings(User user) {
		return bookingDao.clientBookings(user.getId());
	}

}
