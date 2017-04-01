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
		
}
