package com.shareanycar.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shareanycar.dto.LocationDto;
import com.shareanycar.model.Location;
import com.shareanycar.service.LocationService;
import com.shareanycar.service.OwnerService;

/*
 * get /api/location/all
 * get /api/location/countries
 * get /api/location/cities
 */

@Path("/location")
public class LocationController {

	@Inject
	public LocationService locationService;
	
	@Inject
	public OwnerService ownerService;

	@GET @Path("/all")
	@Produces({ MediaType.APPLICATION_JSON})
	public Set<LocationDto> allLoctions(){
		Set<LocationDto> locDto = new HashSet<>();
		for(Location loc : locationService.findAll()){
			locDto.add(new LocationDto(loc.getId(),loc.getCountry(),loc.getCity()));
		}
		return locDto;
	}
	
	@GET @Path("/contries")
	@Produces({ MediaType.APPLICATION_JSON})
	public Set<LocationDto> countryLocations(){
		Set<LocationDto> locDto = new HashSet<>();
		for(Location l : locationService.findAll()){
			locDto.add(new LocationDto(null, l.getCountry(), null));
		}
		return locDto;
	}
	
	@GET @Path("/cities")
	@Produces({ MediaType.APPLICATION_JSON})
	public Set<LocationDto> cityLocations(){
		Set<LocationDto> locDto = new HashSet<>();
		for(Location l : locationService.findAll()){
			locDto.add(new LocationDto(null, null, l.getCity()));
		}
		return locDto;
	}
}
