package com.shareanycar.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Brand;
import com.shareanycar.model.Car;
import com.shareanycar.model.Location;
import com.shareanycar.model.Model;
import com.shareanycar.model.Owner;

public class CarService {

	@Inject
	public OwnerDao ownerDao;

	@Inject
	public CarDao carDao ;
	
	@Inject
	public LocationService locationService;
	
	@Inject
	public LocationDao locationDao;
	
	@Inject
	public BrandService brandService;
	
	@Inject
	public ModelService modelService;

	public Car create(Long ownerId, String brandName, String modelName, String name, String description, Integer year, String country,
			String city) {
		
		Owner owner = ownerDao.findOne(ownerId);
		
		if(owner == null){
			throw new IllegalArgumentException("can not find owner with id: "+ ownerId);
		}
		
		Car car = new Car();
		Location loc ;
		
		if(country == null || city == null){
			loc = owner.getLocation();
		}else{
			loc = locationService.prepare(country, city);
		}
		
		Brand brand = brandService.prepare(brandName);
		Model model = modelService.prepare(modelName, brand);
		
		
		car.setName(name);
		car.setDescription(description);
		car.setYear(year);
		car.setLocation(loc);
		car.setOwner(owner);
		car.setBrand(brand);
		car.setModel(model);
		
		
		car = carDao.save(car);
		
		return car;
	}
	
	public Car update(Long ownerId, Long id,String brandName, String modelName, String name, String description,Integer year, String country, String city) {
		Owner owner = ownerDao.findOne(ownerId);
		
		Car car = carDao.findOne(id);
		
		if(owner == null || car == null){
			throw new IllegalArgumentException("can not find owner with id: " + ownerId + " or car with id: " + id);
		}
		
		for (Car c : owner.getCars()) {
			if (c.getId() == car.getId()) {
				Location loc;
				if(country == null || city == null){
					loc = c.getLocation();
				}else{
					loc = locationService.prepare(country, city);
				}
				
				Brand brand = brandService.prepare(brandName);
				Model model = modelService.prepare(modelName, brand);
				
				car.setName(name);
				car.setDescription(description);
				car.setLocation(loc);
				car.setYear(year);
				car.setBrand(brand);
				car.setModel(model);
				
				car = carDao.save(car);
				return car;
			}
		}

		return null;
	}
	
	public boolean delete(Long ownerId, Long carId) {
		Owner owner = ownerDao.findOne(ownerId); 
		Car car = carDao.findOne(carId);
		
		if(owner == null || car == null){
			throw new IllegalArgumentException("can not find owner with id: " + ownerId + " or car with id: " + carId);
		}
		
		for (Car c : owner.getCars()) {
			if (c.getId() == car.getId()) {
				carDao.delete(car);
				return true;
			}

		}
		return false;
	}
	
	public List<Car> findCarByOwnerId(Long ownerId){	
		return carDao.findCarByOwnerId(ownerId);
	}
	
	public Car findCarById(Long id){
		return carDao.findOne(id);
	}

	public Set<Car> findCarByCountryAndCity(String country, String city){
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");

		Location loc = locationDao.findByCountryAndCity(country, city);
		return loc.getCars();
	}
	
	public Set<Car> findCarByCountry(String country){
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		Set<Car> cars = new HashSet<>();
		
		List<Location> locs = locationDao.findByCountry(country);
		for(Location l : locs){
			cars.addAll(l.getCars());
		}
		return cars;
	}
	
	public Set<Car> findCarByCity(String city){
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");
		Set<Car> cars = new HashSet<>();
		
		List<Location> locs = locationDao.findByCity(city);
		for(Location l : locs){
			cars.addAll(l.getCars());
		}
		return cars;
	}
}
