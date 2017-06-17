package com.shareanycar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import com.shareanycar.dao.CarTypeDao;
import com.shareanycar.dao.FuelTypeDao;
import com.shareanycar.dao.InsurerDao;
import com.shareanycar.dao.ManufacturerDao;
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.dto.PropertyDto;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Insurer;
import com.shareanycar.model.Manufacturer;
import com.shareanycar.model.TransmissionType;

@Path("property")
public class CarPropertyController {

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
	public ModelMapper modelMapper;
	
	@Inject
	public Logger logger;

	@GET
	@Path("/fuel")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response fuel() {
		try {
			List<PropertyDto> properties = new ArrayList<>();

			for (FuelType f : fuelTypeDao.findAll()) {
				properties.add(new PropertyDto(f.getName()));
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/transmission")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response transmission() {
		try {
			List<PropertyDto> properties = new ArrayList<>();

			for (TransmissionType f : transmissionTypeDao.findAll()) {				
				properties.add(new PropertyDto(f.getName()));
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/insurer")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insurer(){
		try {
			List<PropertyDto> properties = new ArrayList<>();

			for (Insurer f : insurerDao.findAll()) {				
				properties.add(new PropertyDto(f.getName()));
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/manufacturer")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response manufacturer(){
		try {
			List<PropertyDto> properties = new ArrayList<>();

			for (Manufacturer f : manufacturerDao.findAll()) {				
				properties.add(new PropertyDto(f.getName()));
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/car")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response car() {
		try {
			List<PropertyDto> properties = new ArrayList<>();

			for (CarType f : carTypeDao.findAll()) {
				properties.add(new PropertyDto(f.getName()));
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/status")
	@Produces({MediaType.APPLICATION_JSON})
	public Response status(){
		
		try{
			List<PropertyDto> properties = new ArrayList<>();
			
			properties.add(new PropertyDto("ACTIVE"));
			properties.add(new PropertyDto("INACTIVE"));
			
			return Response.ok(properties).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
