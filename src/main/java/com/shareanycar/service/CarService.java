package com.shareanycar.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public Long create(Long ownerId,  String name, String description, Integer year, String transmissionType,
			String carType, Integer numberOfSeats,
			String country, String city) throws Exception {

		Owner owner = ownerDao.findOne(ownerId);

		if (owner == null) {
			throw new Exception("can not find owner with id: " + ownerId);
		}

		Car car = new Car();
		Location loc;

		if (country == null || city == null) {
			loc = owner.getLocation();
		} else {
			loc = locationService.prepare(country, city);
		}

		car.setName(name);
		car.setDescription(description);
		car.setYear(year);
		car.setLocation(loc);
		car.setOwner(owner);
		car.setNumberOfSeats(numberOfSeats);
		car.setTransmissionType(transmissionType);
		car.setCarType(carType);
		
		car = carDao.save(car);

		return car.getId();
	}

	public void update(Long ownerId, Long carId,  String name, String description,
			Integer year,  String transmissionType,
			String carType, Integer numberOfSeats, String country, String city) throws Exception {

		Car car = carDao.findOne(carId);

		if (car.getOwner().getId() != ownerId) {
			throw new Exception("car does not belong to current owner id:" + ownerId);
		}

		Location loc;
		if (country == null || city == null) {
			loc = car.getLocation();
		} else {
			loc = locationService.prepare(country, city);
		}
		
		car.setName(name);
		car.setDescription(description);
		car.setLocation(loc);
		car.setYear(year);
		car.setCarType(carType);
		car.setTransmissionType(transmissionType);
		car.setNumberOfSeats(numberOfSeats);
		
		car = carDao.save(car);

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

	}

	public List<Car> findCarByOwnerId(Long ownerId) {
		return carDao.findCarByOwnerId(ownerId);
	}

	public Car findCarById(Long id) {
		return carDao.findOne(id);
	}

	public Set<Car> findCarByCountryAndCity(String country, String city) {
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");

		Location loc = locationDao.findByCountryAndCity(country, city);
		return loc.getCars();
	}

	public Set<Car> findCarByCountry(String country) {
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		Set<Car> cars = new HashSet<>();

		List<Location> locs = locationDao.findByCountry(country);
		for (Location l : locs) {
			cars.addAll(l.getCars());
		}
		return cars;
	}

	public Set<Car> findCarByCity(String city) {
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");
		Set<Car> cars = new HashSet<>();

		List<Location> locs = locationDao.findByCity(city);
		for (Location l : locs) {
			cars.addAll(l.getCars());
		}
		return cars;
	}
}
