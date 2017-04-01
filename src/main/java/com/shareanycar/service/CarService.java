package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CarTypeDao;
import com.shareanycar.dao.FuelTypeDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Image;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;
import com.shareanycar.model.TransmissionType;

public class CarService {

	@Inject
	public OwnerDao ownerDao;

	@Inject
	public CarDao carDao;

	@Inject
	public LocationService locationService;

	@Inject
	public LocationDao locationDao;

	@Inject
	public FuelTypeDao fuelTypeDao;

	@Inject
	public TransmissionTypeDao transmissionTypeDao;

	@Inject
	public CarTypeDao carTypeDao;

	@Inject
	public ImageService imageService;

	@Inject
	public ImageDao imageDao;

	
	private Car setCarProperties(Car car, Location location, TransmissionType transmissionType, CarType carType, FuelType fuelType) throws Exception{
		location = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
		carType = carTypeDao.findByName(carType.getName());
		transmissionType = transmissionTypeDao.findByName(transmissionType.getName());
		fuelType = fuelTypeDao.findByName(fuelType.getName());

		if (location == null || carType == null || transmissionType == null || fuelType == null) {
			throw new Exception("can not find car property");
		}

		car.setLocation(location);
		car.setTransmissionType(transmissionType);
		car.setCarType(carType);
		car.setFuelType(fuelType);
		
		return car;
	}
	
	public Long create(Long ownerId, Car car, Location location, TransmissionType transmissionType, CarType carType,
			FuelType fuelType) throws Exception {
		Owner owner = ownerDao.findOne(ownerId);

		if (owner == null) {
			throw new Exception("can not find owner with id: " + ownerId);
		}

		car = setCarProperties(car, location, transmissionType, carType, fuelType);
		car.setOwner(owner);

		car = carDao.save(car);

		return car.getId();

	}

	public void update(Long ownerId, Long carId, Car car, Location location, TransmissionType transmissionType, CarType carType, FuelType fuelType) throws Exception {

		if (car == null) {
			throw new Exception("no update car provided");
		}

		if (location == null) {
			throw new Exception("no update location provided");
		}

		Car currentCar = carDao.findOne(carId);

		if (currentCar == null) {
			throw new Exception("can not find car");
		}

		if (currentCar.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}

		currentCar = setCarProperties(currentCar, location, transmissionType, carType, fuelType);

		currentCar.setName(car.getName());
		currentCar.setDescription(car.getDescription());
		currentCar.setFeatures(car.getFeatures());
		currentCar.setMileage(car.getMileage());
		currentCar.setNumberOfSeats(car.getNumberOfSeats());
		currentCar.setPrice(car.getPrice());
		currentCar.setYear(car.getYear());
		currentCar.setStatus(car.isStatus());

		currentCar = carDao.save(currentCar);

	}

	public void delete(Long ownerId, Long carId) throws Exception {
		Car car = carDao.findOne(carId);

		if (car.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}

		for (Image image : car.getImages()) {
			imageService.delete(image.getId(), car.getId(), ownerId, false);
		}
		carDao.delete(car);

	}

	public void setDefaultImage(Long imageId, Long carId, Long ownerId) throws Exception {
		Image image = imageDao.findOne(imageId);
		if (image == null) {
			throw new Exception("can not find image with id:" + imageId);
		}

		if (image.getCar().getOwner().getId() != ownerId) {
			throw new Exception("image does not belong to current owner id:" + imageId);
		}

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);

		}

		if (car.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}

		car.setDefaultImageUrl(image.getUrlSmall());
		carDao.save(car);

	}

	public List<Car> findCarByOwnerId(Long ownerId) {
		return carDao.findCarByOwnerId(ownerId);
	}

	public Car findCarById(Long id) {
		return carDao.findOne(id);
	}

}
