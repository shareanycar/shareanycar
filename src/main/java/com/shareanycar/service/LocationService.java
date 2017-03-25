package com.shareanycar.service;

import javax.inject.Inject;

import com.shareanycar.dao.LocationDao;
import com.shareanycar.model.Location;


public class LocationService {
	@Inject
	public LocationDao locationDao;
	
	public Iterable<Location> findAll(){
		return locationDao.findAll();
	}
	
	
	public Location prepare(String country, String city) {
		country = country.trim().toUpperCase().replaceAll("\\s+", " ");
		city = city.trim().toUpperCase().replaceAll("\\s+", " ");

		Location location = locationDao.findByCountryAndCity(country, city);
		if (location == null) {
			location = new Location();
			location.setCountry(country);
			location.setCity(city);
			location = locationDao.save(location);
		}
		return location;
	}

}
