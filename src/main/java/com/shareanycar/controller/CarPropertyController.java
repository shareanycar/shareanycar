package com.shareanycar.controller;

import java.util.LinkedList;
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
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.dto.CarTypeDto;
import com.shareanycar.dto.FuelTypeDto;
import com.shareanycar.dto.TransmissionTypeDto;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
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
	public ModelMapper modelMapper;
	
	@Inject
	public Logger logger;

	@GET
	@Path("/fuel")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response fuel() {
		try {
			List<FuelTypeDto> properties = new LinkedList<>();

			for (FuelType f : fuelTypeDao.findAll()) {
				FuelTypeDto propertyDto = modelMapper.map(f, FuelTypeDto.class);
				properties.add(propertyDto);
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
			List<TransmissionTypeDto> properties = new LinkedList<>();

			for (TransmissionType f : transmissionTypeDao.findAll()) {
				
				TransmissionTypeDto propertyDto = modelMapper.map(f, TransmissionTypeDto.class);
				properties.add(propertyDto);
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
			List<CarTypeDto> properties = new LinkedList<>();

			for (CarType f : carTypeDao.findAll()) {
				CarTypeDto propertyDto = modelMapper.map(f, CarTypeDto.class);
				properties.add(propertyDto);
			}

			return Response.ok(properties).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
