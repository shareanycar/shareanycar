package com.shareanycar.service;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.enums.BookingStatus;
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
		booking.setStatus(BookingStatus.NEW);
		
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

	public void confirmBooking(Long id, User user) throws Exception {
		Booking booking = bookingDao.findOne(id);

		if (booking.getCar().getUser().getId() != user.getId()) {
			throw new Exception("current user is not allowed to confirm this booking");
		}

		if (booking.getStatus() == BookingStatus.NEW) {
			booking.setStatus(BookingStatus.CONFIRMED);
			booking = bookingDao.save(booking);
		} else {
			throw new Exception("can not confirm booking with such status");
		}
	}

	public void cancelBooking(Long id, User user) throws Exception {
		Booking booking = bookingDao.findOne(id);

		if (booking.getUser().getId() != user.getId()) {
			throw new Exception("current user is not allowed to cancel this booking");
		}

		if (booking.getStatus() == BookingStatus.NEW || booking.getStatus() == BookingStatus.CONFIRMED) {
			booking.setStatus(BookingStatus.CANCELED);
			booking = bookingDao.save(booking);
		}else{
			throw new Exception("can not cancel booking with such status");
		}
	}

	public Booking viewBooking(Long id, User user) throws Exception {
		Booking booking = bookingDao.findOne(id);
		
		if(booking.getUser().getId() == user.getId()){
			return booking;
		}
		
		if(booking.getCar().getId() == user.getId()){
			return booking;
		}
		
		throw new Exception("current user is not allowed to view this booking");
	}

}
