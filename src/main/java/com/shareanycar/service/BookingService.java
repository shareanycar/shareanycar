package com.shareanycar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

	private int calcNumberOfDays(LocalDate dateFrom, LocalDate dateTo) {

		return dateFrom.until(dateTo).getDays() + 1;
	}

	public void book(Booking booking, User user, Long carId) throws Exception {
		if (booking.getDateFrom().compareTo(booking.getDateTo()) >= 0) {
			throw new Exception("incorrent dateFrom and dateTo dates");
		}

		Car car = carService.findById(carId);

		if (car == null) {
			throw new Exception("can not find such car:" + carId);
		}

		booking.setUser(user);
		booking.setCar(car);
		booking.setStatus(BookingStatus.NEW);

		int numberOfDays = calcNumberOfDays(booking.getDateFrom(), booking.getDateTo());
		booking.setNumberOfDays(numberOfDays);
		booking.setTotalPrice(numberOfDays * car.getPrice());

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

		List<Booking> bookings = new ArrayList<>(car.getBookings());

		Collections.sort(bookings, Collections.reverseOrder());

		return bookings;
	}

	public List<Booking> ownerBookings(User user) {
		List<Booking> bookings = new ArrayList<>();

		for (Car c : user.getCars()) {
			bookings.addAll(c.getBookings());
		}

		Collections.sort(bookings, Collections.reverseOrder());
		return bookings;
	}

	public List<Booking> clientBookings(User user) {
		List<Booking> bookings = bookingDao.clientBookings(user.getId());
		Collections.sort(bookings, Collections.reverseOrder());

		return bookings;
	}

	public void confirmBooking(Booking booking, User user) throws Exception {

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

	public void cancelBooking(Booking booking, User user) throws Exception {

		if (booking.getUser().getId() != user.getId()) {
			throw new Exception("current user is not allowed to cancel this booking");
		}

		if (booking.getStatus() == BookingStatus.NEW || booking.getStatus() == BookingStatus.CONFIRMED) {
			booking.setStatus(BookingStatus.CANCELED);
			booking = bookingDao.save(booking);
		} else {
			throw new Exception("can not cancel booking with such status");
		}
	}

	public Booking viewBooking(Long id, User user) throws Exception {
		Booking booking = bookingDao.findOne(id);

		if (booking.getUser().getId() == user.getId()) {
			return booking;
		}

		if (booking.getCar().getId() == user.getId()) {
			return booking;
		}

		throw new Exception("current user is not allowed to view this booking");
	}

	public Booking findOne(Long id) {
		return bookingDao.findOne(id);
	}

}
