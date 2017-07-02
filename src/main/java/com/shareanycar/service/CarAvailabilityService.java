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

public class CarAvailabilityService {

	@Inject
	public CarAvailabilityDao carAvailabilityDao;
	
	@Inject
	public CarDao carDao;

	private List<LocalDate> prepareDates(LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = new ArrayList<>();

		LocalDate tmpDate = dateFrom.plusDays(1);
		while (tmpDate.compareTo(dateTo) <= 1) {
			dates.add(tmpDate);
			tmpDate = tmpDate.plusDays(1);
		}
		return dates;
	}
	
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
		
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		Car car = carDao.findOne(carId);

		for (LocalDate date : dates) {
			CarAvailability carAvailability = carAvailabilityDao.findCarAvailabilityByParams(carId, date);
			
			if(carAvailability == null && car.getDefaultAvailability() != AvailabilityStatus.AVAILABLE){
				return false;
			}
			
			if (carAvailability != null && carAvailability.getAvailability() != AvailabilityStatus.AVAILABLE) {
				return false;
			}
		}
		return true;
	}

	private  List<CarAvailability> carAvailability(Car car){
		List<CarAvailability> list = new ArrayList<>();
		list.addAll(car.getCarAvailability());
		return list;
	}
	
	public List<CarAvailability> getAvailability(Car car){
		return carAvailability(car);
	}
	
	public List<CarAvailability> getAvailability(Long carId){
		Car car = carDao.findOne(carId);
		return carAvailability(car);
	} 
	
	public void setCarBooked(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.BOOKED);
	}

	public void setCarAvailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.AVAILABLE);
	}

	public void setCarUnavailable(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		List<LocalDate> dates = prepareDates(dateFrom, dateTo);
		setCarAvailability(carId, dates, AvailabilityStatus.NOTAVAILABLE);
	}

}
