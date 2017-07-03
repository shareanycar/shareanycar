package com.shareanycar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.CarAvailabilityDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.enums.AvailabilityStatus;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarAvailability;
import com.shareanycar.util.MiscUtils;

public class CarAvailabilityService {

	@Inject
	public CarAvailabilityDao carAvailabilityDao;

	@Inject
	public CarDao carDao;

	@Inject
	public MiscUtils miscUtils;

	private void setCarAvailability(Long carId, List<LocalDate> dates, AvailabilityStatus availability) {
		for (LocalDate date : dates) {

			CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(carId, date);

			if (carAvailability == null) {
				Car car = carDao.findOne(carId);
				carAvailability = new CarAvailability(date, availability, car);
			} else {
				carAvailability.setAvailability(availability);
			}

			carAvailabilityDao.save(carAvailability);
		}
	}

	public boolean isAvailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {

		List<LocalDate> dates = miscUtils.listOfDates(dateFrom, dateTo);
		Car car = carDao.findOne(carId);

		for (LocalDate date : dates) {
			CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(carId, date);

			if (carAvailability == null && car.getDefaultAvailability() != AvailabilityStatus.AVAILABLE) {
				return false;
			}

			if (carAvailability != null && carAvailability.getAvailability() != AvailabilityStatus.AVAILABLE) {
				return false;
			}
		}
		return true;
	}

	private List<CarAvailability> carAvailability(Car car) {
		List<CarAvailability> list = new ArrayList<>();
		list.addAll(car.getCarAvailability());
		return list;
	}

	public List<CarAvailability> getAvailability(Car car) {
		return carAvailability(car);
	}

	public List<CarAvailability> getAvailability(Long carId) {
		Car car = carDao.findOne(carId);
		return carAvailability(car);
	}

	public List<CarAvailability> getAvailability(Long carId, LocalDate fromDate, LocalDate toDate) {

		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);
		List<CarAvailability> listAvailability = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);

		if (listAvailability.size() != dates.size()) {
			listAvailability = new ArrayList<>();
			Car car = carDao.findOne(carId);
			for (LocalDate date : dates) {
				CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(carId, date);
				if (carAvailability == null) {
					carAvailability = new CarAvailability(date, car.getDefaultAvailability(), car);
					carAvailabilityDao.save(carAvailability);
				}
				listAvailability.add(carAvailability);
			}
		}
		return listAvailability;
	}

	public void setCarBooked(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = miscUtils.listOfDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.BOOKED);
	}

	public void setCarAvailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = miscUtils.listOfDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.AVAILABLE);
	}

	public void setCarUnavailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = miscUtils.listOfDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.NOTAVAILABLE);
	}

}
