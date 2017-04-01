package com.shareanycar.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;

import com.shareanycar.dto.LocationDto;
import com.shareanycar.dto.PropertyDto;
import com.shareanycar.model.Location;
import com.shareanycar.service.LocationService;

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
	public ModelMapper modelMapper;
	
	@GET 
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response allLocations(){
		Set<LocationDto> locations = new HashSet<>();
		for(Location l : locationService.findAll()){
			LocationDto loc = modelMapper.map(l, LocationDto.class);
			locations.add(loc);
		}
		return Response.ok(locations).build();
	}
	
	@GET @Path("/countries")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response countryLocations(){
		Set<PropertyDto> locDto = new HashSet<>();
		for(Location l : locationService.findAll()){
			locDto.add(new PropertyDto(l.getCountry()));
		}
		return Response.ok(locDto).build();
	}
	
	@GET @Path("/country/{country}/cities")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response cityLocations(@PathParam("country") String country){
		Set<PropertyDto> locDto = new HashSet<>();
		for(Location l : locationService.findCitiesByCountry(country)){
			locDto.add(new PropertyDto(l.getCity()));
		}
		return Response.ok(locDto).build();
	}
}
