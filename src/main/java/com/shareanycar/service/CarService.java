package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CarTypeDao;
import com.shareanycar.dao.FuelTypeDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.InsurerDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.ManufacturerDao;
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.dao.UserDao;
import com.shareanycar.enums.CarStatus;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Image;
import com.shareanycar.model.Insurer;
import com.shareanycar.model.Location;
import com.shareanycar.model.Manufacturer;
import com.shareanycar.model.TransmissionType;
import com.shareanycar.model.User;

public class CarService {

	@Inject
	public UserDao userDao;

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
	public InsurerDao insurerDao;
	
	
	@Inject
	public ManufacturerDao manufacturerDao;

	@Inject
	public ImageService imageService;
	
	

	@Inject
	public ImageDao imageDao;

	private Car setCarProperties(Car car, Location location, TransmissionType transmissionType, CarType carType,
			FuelType fuelType, Manufacturer manufacturer, Insurer insurer) throws Exception {

		location = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
		carType = carTypeDao.findByName(carType.getName());
		transmissionType = transmissionTypeDao.findByName(transmissionType.getName());
		fuelType = fuelTypeDao.findByName(fuelType.getName());
		manufacturer = manufacturerDao.findByName(manufacturer.getName());
		insurer = insurerDao.findByName(insurer.getName());

		if (location == null || carType == null || transmissionType == null || fuelType == null || manufacturer == null
				|| insurer == null) {
			throw new Exception("can not find car property");
		}

		car.setLocation(location);
		car.setTransmissionType(transmissionType);
		car.setCarType(carType);
		car.setFuelType(fuelType);
		car.setInsurer(insurer);
		car.setManufacturer(manufacturer);

		return car;
	}

	public Long create(Long userId, Car car, Location location, TransmissionType transmissionType, CarType carType,
			FuelType fuelType, Manufacturer manufacturer, Insurer insurer) throws Exception {
		
		User user = userDao.findOne(userId);

		if (user == null) {
			throw new Exception("can not find owner with id: " + userId);
		}

		car = setCarProperties(car, location, transmissionType, carType, fuelType, manufacturer, insurer);
		car.setStatus(CarStatus.NEW);
		car.setUser(user);

		car = carDao.save(car);

		return car.getId();

	}

	public void update(Long userId, Long carId, Car car, Location location, TransmissionType transmissionType,
			CarType carType, FuelType fuelType, Manufacturer manufacturer, Insurer insurer) throws Exception {

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

		if (currentCar.getUser().getId() != userId) {
			throw new Exception("car does not belong to current user id:" + userId);
		}

		currentCar = setCarProperties(currentCar, location, transmissionType, carType, fuelType, manufacturer, insurer);

		currentCar.setDescription(car.getDescription());
		currentCar.setMileage(car.getMileage());
		currentCar.setNumberOfSeats(car.getNumberOfSeats());
		currentCar.setPrice(car.getPrice());
		currentCar.setYear(car.getYear());
		currentCar.setModelName(car.getModelName());
		currentCar.setLicensePlateNumber(car.getLicensePlateNumber());
		currentCar.setStatus(car.getStatus());

		currentCar = carDao.save(currentCar);

	}

	public void delete(Long userId, Long carId) throws Exception {
		Car car = carDao.findOne(carId);

		if (car.getUser().getId() != userId) {
			throw new Exception("car does not belong to current owner id:" + userId);
		}

		for (Image image : car.getImages()) {
			imageService.deleteCarImage(image.getId(), car.getId(), userId, false);
		}
		carDao.delete(car);

	}

	public void setDefaultImage(Long imageId, Long carId, Long userId) throws Exception {
		Image image = imageDao.findOne(imageId);
		if (image == null) {
			throw new Exception("can not find image with id:" + imageId);
		}

		if (image.getCar().getUser().getId() != userId) {
			throw new Exception("image does not belong to current owner id:" + imageId);
		}

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);
		}

		if (car.getUser().getId() != userId) {
			throw new Exception("car does not belong to current owner id:" + userId);
		}
		carDao.save(car);

	}

	public Car findById(Long id) {
		return carDao.findOne(id);
	}
	
	public List<Car> findAvailable(){
		return carDao.findAvailable();
	}
	
	public List<Car> findAll(){
		return carDao.findAll();
	}

}
