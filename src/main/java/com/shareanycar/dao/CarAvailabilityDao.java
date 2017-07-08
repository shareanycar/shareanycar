package com.shareanycar.dao;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import com.shareanycar.model.CarAvailability;

public class CarAvailabilityDao extends BasicDao<CarAvailability> {

	@Inject
	public ExtDao<?> extDao;

	public CarAvailabilityDao() {
		super("CarAvailability");
	}

	@SuppressWarnings("unchecked")
	public List<CarAvailability> findCarAvailabilityByCarId(Long carId) {
		return (List<CarAvailability>) extDao.findListByParam("from CarAvailability where car_id = :car_id", "car_id",
				carId);
	}

	public CarAvailability findCarAvailabilityByParams(Long carId, LocalDate date) {
		return (CarAvailability) extDao.findOneByParams("from CarAvailability where car_id = :car_id and date = :date",
				new String[] { "car_id", "date" }, new Object[] { carId, date });
	}

	@SuppressWarnings("unchecked")
	public List<CarAvailability> findCarAvailablityByParams(Long carId, LocalDate dateFrom, LocalDate dateTo) {
		return (List<CarAvailability>) extDao.findListByParams(
				"from CarAvailability where car_id = :car_id and date >= :date_from and date <= :date_to",
				new String[] { "car_id", "date_from", "date_to" }, new Object[] { carId, dateFrom, dateTo });

	}
}
