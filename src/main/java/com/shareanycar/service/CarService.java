package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;

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
	public ImageService imageService;
		

	@Inject
	public ImageDao imageDao;
	
	
	public Long create(Long ownerId, Car car, Location location) throws Exception{
		Owner owner = ownerDao.findOne(ownerId);
		
		if (owner == null) {
			throw new Exception("can not find owner with id: " + ownerId);
		}
		
		Location setLocation = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
		
		if(setLocation == null){
			setLocation = locationDao.save(location);
		}
		
		car.setLocation(setLocation);
		car.setOwner(owner);

		car = carDao.save(car);
		
		return car.getId();
		
	}
	
	

	public void update(Long ownerId, Long carId, Car car, Location location) throws Exception{

		if(car == null){
			throw new Exception("no update car provided");
		}
		
		if(location == null){
			throw new Exception("no update location provided");
		}
		
		Car currentCar = carDao.findOne(carId);

		if(currentCar == null){
			throw new Exception("can not find car");
		}
		

		if (currentCar.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}

		Location setLocation = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());

		if(setLocation == null){
			setLocation = locationDao.save(location);
		}
		
		currentCar.setLocation(setLocation);
		currentCar.setName(car.getName());
		currentCar.setDescription(car.getDescription());
		currentCar.setFeatures(car.getFeatures());
		currentCar.setFuelType(car.getFuelType());
		currentCar.setMileage(car.getMileage());
		currentCar.setNumberOfSeats(car.getNumberOfSeats());
		currentCar.setTransmissionType(car.getTransmissionType());
		currentCar.setPrice(car.getPrice());
		currentCar.setYear(car.getYear());
		currentCar.setStatus(car.isStatus());
		currentCar.setCarType(car.getCarType());
		
		currentCar = carDao.save(currentCar);
		
	}
	
	public void delete(Long ownerId, Long carId) throws Exception {
		Car car = carDao.findOne(carId);

		if (car.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}
		
		for(Image image: car.getImages()){
			imageService.delete(image.getId(), car.getId(), ownerId, false);
		}
		carDao.delete(car);

	}

	public void setDefaultImage(Long imageId, Long carId, Long ownerId) throws Exception {
		Image image = imageDao.findOne(imageId);
		if (image == null) {
			throw new Exception("can not find image with id:" + imageId);
		}

		if(image.getCar().getOwner().getId() != ownerId){
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
