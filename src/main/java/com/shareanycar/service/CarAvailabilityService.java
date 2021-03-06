package com.shareanycar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public MiscUtils miscUtils;

	private void setCarAvailability(Long carId, LocalDate fromDate, LocalDate toDate, AvailabilityStatus availability) {

		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);

		Map<LocalDate, CarAvailability> availabilityMap = new HashMap<>();
		for (CarAvailability a : carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate)) {
			availabilityMap.put(a.getDate(), a);
		}

		Car car = carDao.findOne(carId);
		List<CarAvailability> list = new ArrayList<>();
		for (LocalDate d : dates) {
			if (availabilityMap.containsKey(d)) {
				availabilityMap.get(d).setAvailability(availability);
				list.add(availabilityMap.get(d));
			} else {
				list.add(new CarAvailability(d, availability, car));
			}
		}
		carAvailabilityDao.saveAll(list);
	}
	
	public boolean isAvailable(Car car, LocalDate fromDate, LocalDate toDate){
		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);

		Map<LocalDate, CarAvailability> availabilityMap = new HashMap<>();
		for (CarAvailability a : carAvailabilityDao.findCarAvailablityByParams(car.getId(), fromDate, toDate)) {
			availabilityMap.put(a.getDate(), a);
		}

		for (LocalDate d : dates) {
			if (availabilityMap.containsKey(d)) {
				if (availabilityMap.get(d).getAvailability() != AvailabilityStatus.AVAILABLE) {
					return false;
				}
			} else {
				if (car.getDefaultAvailability() != AvailabilityStatus.AVAILABLE) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isAvailable(Long carId, LocalDate fromDate, LocalDate toDate) {
		Car car = carDao.findOne(carId);
		return isAvailable(car, fromDate, toDate);
	}

	public List<CarAvailability> getAvailability(Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		List<LocalDate> dates = miscUtils.listOfDates(fromDate, toDate);

		if (dates.size() >= 100) {
			throw new Exception("dates are too far away from each other: " + fromDate + "::" + toDate);
		}

		Car car = carDao.findOne(carId);
		List<CarAvailability> list = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);

		if (dates.size() != list.size()) {
			Set<LocalDate> availabilityDates = new HashSet<>();

			for (CarAvailability a : list) {
				availabilityDates.add(a.getDate());
			}

			for (LocalDate d : dates) {
				if (!availabilityDates.contains(d)) {
					CarAvailability e = new CarAvailability(d, car.getDefaultAvailability(), car);
					list.add(e);
				}
			}
			carAvailabilityDao.saveAll(list);
			list = carAvailabilityDao.findCarAvailablityByParams(carId, fromDate, toDate);
		}
		
		Collections.sort(list, new Comparator<CarAvailability>() {
			@Override
			public int compare(CarAvailability o1, CarAvailability o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		return list;
	}

	public void setCarBooked(Long carId, LocalDate fromDate, LocalDate toDate) {
		setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.BOOKED);
	}

	public void setCarAvailable(User user, Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		if (carDao.findOne(carId).getUser().getId() == user.getId()) {
			setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.AVAILABLE);
		} else {
			throw new Exception("car does not belong to user");
		}
	}

	public void setCarUnavailable(User user, Long carId, LocalDate fromDate, LocalDate toDate) throws Exception {
		if (carDao.findOne(carId).getUser().getId() == user.getId()) {
			setCarAvailability(carId, fromDate, toDate, AvailabilityStatus.NOTAVAILABLE);
		} else {
			throw new Exception("car does not belong to user");
		}
	}

}
