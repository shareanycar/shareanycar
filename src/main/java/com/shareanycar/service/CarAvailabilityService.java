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
import com.shareanycar.model.User;
import com.shareanycar.util.MiscUtils;

public class CarAvailabilityService {

	@Inject
	public CarAvailabilityDao carAvailabilityDao;

	@Inject
	public CarDao carDao;

	@Inject
	public CarService carService;

	@Inject
	public MiscUtils miscUtils;

	private void setCarAvailability(Long carId, LocalDate fromDate, LocalDate toDate, AvailabilityStatus availability) {

		Car car = carDao.findOne(carId);
		List<CarAvailability> list = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);
		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);

		for (int i = 0; i < dates.size(); i++) {
			if (list.size() <= i) {
				CarAvailability e = new CarAvailability(dates.get(i), availability, car);
				list.add(e);
				continue;
			}

			if(dates.get(i).equals(list.get(i).getDate())){
				list.get(i).setAvailability(availability);
			}else{
				CarAvailability e = new CarAvailability(dates.get(i), availability, car);
				list.add(i,e);
			}
		}
		
		carAvailabilityDao.saveAll(list);
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

	public List<CarAvailability> getAvailability(Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);

		if (dates.size() >= 100) {
			throw new Exception("dates are too far away from each other: " + fromDate + "::" + toDate);
		}

		Car car = carDao.findOne(carId);
		List<CarAvailability> list = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);

		if (dates.size() != list.size()) {
			for (int i = 0; i < dates.size(); i++) {
				if (list.size() <= i) {
					CarAvailability e = new CarAvailability(dates.get(i), car.getDefaultAvailability(), car);
					list.add(e);
					continue;
				}

				if (!dates.get(i).equals(list.get(i).getDate())) {
					CarAvailability e = new CarAvailability(dates.get(i), car.getDefaultAvailability(), car);
					list.add(i, e);
				}
			}
			carAvailabilityDao.saveAll(list);
			list = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);
		}

		return list;
	}

	public void setCarBooked(Long carId, LocalDate fromDate, LocalDate toDate) {
		setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.BOOKED);
	}

	public void setCarAvailable(User user, Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		if (carService.belongsTo(user, carId)) {
			setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.AVAILABLE);
		} else {
			throw new Exception("car does not belong to user");
		}
	}

	public void setCarUnavailable(User user, Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		if (carService.belongsTo(user, carId)) {
			setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.NOTAVAILABLE);
		} else {
			throw new Exception("car does not belong to user");
		}
	}

}
