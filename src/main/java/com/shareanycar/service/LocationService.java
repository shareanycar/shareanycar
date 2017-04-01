package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.dao.LocationDao;
import com.shareanycar.model.Location;


public class LocationService {
	@Inject
	public LocationDao locationDao;
	
	
	
	public List<Location> findAll(){
		return locationDao.findAll();
	}
	
	public List<Location> findCitiesByCountry(String country){
		return locationDao.findByCountry(country);
	}
		
}
