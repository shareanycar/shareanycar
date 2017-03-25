package com.shareanycar.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;

@Service
public class OwnerService {

	@Inject
	public OwnerDao ownerDao ;

	@Inject
	public CarDao carDao ;
	@Inject
	public LocationDao locationDao ;

	@Inject
	public LocationService locationService ;
	
	@Inject
	public ImageDao imageDao ;

	public Owner create(String firstName, String lastName, String country, String city, String phone, String email,
			String password) {

		
		
		Owner owner = ownerDao.findOwnerByEmail(email);

		if (owner != null) {
			throw new IllegalArgumentException("user with such email already exists");
		}

		Location location = locationService.prepare(country, city);

		owner = new Owner();

		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPassword(password);
		owner.setPhone(phone);
		owner.setLocation(location);
		owner = ownerDao.save(owner);

		return owner;
	}

	public Owner update(Long ownerId, String firstName, String lastName, String country, String city, String phone){
		Owner owner = ownerDao.findOne(ownerId);
		if(owner == null){
			throw new IllegalArgumentException("can not find owner with id:" + ownerId);
		}
		
		Location loc;
		if(country == null || city == null){
			loc = owner.getLocation();
		}else{
			loc = locationService.prepare(country, city);
		}
		
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setLocation(loc);
		owner.setPhone(phone);
		
		ownerDao.save(owner);
		return owner;
	}
	
	public Owner updatePassword(Long ownerId, String oldPassword, String newPassword){
		Owner owner = ownerDao.findOne(ownerId);
		if(owner == null){
			throw new IllegalArgumentException("can not find owner with id:" + ownerId);
		}
		
		if(!owner.getPassword().equals(oldPassword)){
			throw new IllegalArgumentException("wrong password");
		}
		
		owner.setPassword(newPassword);
		ownerDao.save(owner);
		
		return owner;
	}
	
	

	public Image createCarImage(Long ownerId, Long carId, String url) {
		Owner owner = ownerDao.findOne(ownerId);
		Car car = carDao.findOne(carId);
		
		if(owner == null || car == null){
			throw new IllegalArgumentException("can not find owner with id: " + ownerId + " or car with id: " + carId);
		}
		
		for (Car c : owner.getCars()) {
			if (c.getId() == car.getId()) {
				Image image = new Image();
				image.setCar(car);
				image.setUrl(url);
				image = imageDao.save(image);
				return image;
			}
		}
		return null;
	}
	
	public boolean removeImage(Long ownerId, Long carId, Long imageId){
		
		Owner owner = ownerDao.findOne(ownerId);
		Car car = carDao.findOne(carId);
		Image image = imageDao.findOne(imageId);
		
		if(owner == null || car == null || image == null){
			throw new IllegalArgumentException("can not find owner with id: " + ownerId + 
					" or car with id: " + carId + " or image with id: " + imageId);
		}
		
		for(Car c : owner.getCars()){
			if(c.getId() == car.getId()){
				for(Image i : c.getImages()){
					if(i.getId() == image.getId()){
						imageDao.delete(image);
						return true;
					}
				}
			}
		}
		return false;
	}

	public Car changeCarStatus(Long ownerId, Long carId, boolean newCarStatus) {
		Owner owner = ownerDao.findOne(ownerId); 
		Car car = carDao.findOne(carId);
		
		if(owner == null || car == null){
			throw new IllegalArgumentException("can not find owner with id: " + ownerId + " or car with id: " + carId);
		}
		
		for (Car c : owner.getCars()) {
			if (c.getId() == car.getId()) {
				car.setStatus(newCarStatus);
				car = carDao.save(car);
				return car;
			}
		}
		return null;
	}

	

	public Owner findOwnerByEmail(String email) {
		return ownerDao.findOwnerByEmail(email);
	}
	
	public Set<Owner> findOwnersByCountryAndCity(String country, String city){
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");

		Location loc = locationDao.findByCountryAndCity(country, city);
		return loc.getOwners();
	}
	
	public Set<Owner> findOwnersByCountry(String country){
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");

		Set<Owner> owners = new HashSet<>();
		for(Location l : locationDao.findByCountry(country)){
			owners.addAll(l.getOwners());
		}
		return owners;
	}
	
	public Set<Owner> findOwnersByCity(String city){
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");

		Set<Owner> owners = new HashSet<>();
		for(Location l : locationDao.findByCity(city)){
			owners.addAll(l.getOwners());
		}
		return owners;
	}
	
	public Owner findOwnerById(Long id){
		return ownerDao.findOne(id);
	}
	

}
