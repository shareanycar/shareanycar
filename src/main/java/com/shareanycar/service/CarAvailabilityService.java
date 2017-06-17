package com.shareanycar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.CarAvailabilityDao;
import com.shareanycar.enums.AvailabilityStatus;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarAvailability;

public class CarAvailabilityService {

	@Inject
	public CarAvailabilityDao carAvailabilityDao;

	private void setCarAvailability(Car car, List<LocalDate> dates, AvailabilityStatus availability) {
		for (LocalDate date : dates) {

			CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(car.getId(), date);
			if (carAvailability == null) {
				carAvailability = new CarAvailability(date, availability, car);
			} else {
				carAvailability.setAvailability(availability);
			}

			carAvailabilityDao.save(carAvailability);
		}
	}

	private List<LocalDate> prepareDates(LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = new ArrayList<>();

		LocalDate tmpDate = dateFrom.plusDays(0);
		while (tmpDate.compareTo(dateTo) <= 0) {
			dates.add(tmpDate);
			tmpDate = tmpDate.plusDays(1);
		}
		return dates;
	}

	public boolean isAvailable(Car car, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		for (LocalDate date : dates) {
			CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(car.getId(), date);
			if (carAvailability != null && carAvailability.getAvailability() != AvailabilityStatus.AVAILABLE) {
				return false;
			}
		}
		return true;
	}

	public void setCarBooked(Car car, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(car, dates, AvailabilityStatus.BOOKED);
	}

	public void setCarAvailable(Car car, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(car, dates, AvailabilityStatus.AVAILABLE);
	}

	public void setCarUnavailable(Car car, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(car, dates, AvailabilityStatus.NOTAVAILABLE);
	}

}
