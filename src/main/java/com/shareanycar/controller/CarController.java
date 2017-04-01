package com.shareanycar.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import com.shareanycar.annotation.SecuredOwner;
import com.shareanycar.dto.CarDto;
import com.shareanycar.model.Car;
import com.shareanycar.model.CarType;
import com.shareanycar.model.FuelType;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;
import com.shareanycar.model.TransmissionType;
import com.shareanycar.service.CarService;
import com.shareanycar.service.OwnerService;
import com.shareanycar.util.ContextUtil;

/*
 * services:
 * post /api/car
 * put /api/car/{id}
 * get /api/car/{id}
 * delete /api/car/{id}
 */

@Path("/car")
public class CarController {

	@Inject
	public OwnerService ownerService;

	@Inject
	public ContextUtil contextHelper;

	@Inject
	public CarService carService;

	@Inject
	public Logger logger;

	@Inject
	public ModelMapper modelMapper;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredOwner
	public Response create(CarDto carDto, @Context SecurityContext securityContext) {
		try {

			Owner owner = contextHelper.getCurrentOwner(securityContext);
			Car car = modelMapper.map(carDto, Car.class);

			Location location = modelMapper.map(carDto, Location.class);
			TransmissionType transmissionType = new TransmissionType(carDto.getTransmissionTypeName());
			CarType carType = new CarType(carDto.getCarTypeName());
			FuelType fuelType = new FuelType(carDto.getFuelTypeName());

			carService.create(owner.getId(), car, location, transmissionType, carType, fuelType);

			return Response.ok().build();
		} catch (Exception e) {
			logger.error("error creating car: " + carDto + " " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response detail(@PathParam("id") Long id) {
		try {
			Car car = carService.findCarById(id);
			if (car == null) {
				throw new Exception("error finding car:" + id);
			}
			CarDto carDto = modelMapper.map(car, CarDto.class);
			System.out.println(carDto);
			return Response.ok(carDto).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredOwner
	public Response update(CarDto carDto, @PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			Owner owner = contextHelper.getCurrentOwner(securityContext);

			Car car = modelMapper.map(carDto, Car.class);
			Location location = modelMapper.map(carDto, Location.class);
			TransmissionType transmissionType = new TransmissionType(carDto.getTransmissionTypeName());
			CarType carType = new CarType(carDto.getCarTypeName());
			FuelType fuelType = new FuelType(carDto.getFuelTypeName());

			carService.update(owner.getId(), id, car, location, transmissionType, carType, fuelType);

			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredOwner
	public Response delete(@PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			Owner owner = contextHelper.getCurrentOwner(securityContext);

			carService.delete(owner.getId(), id);
			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredOwner
	public Response all(@Context SecurityContext securityContext) {
		try {
			Owner owner = contextHelper.getCurrentOwner(securityContext);
			Set<CarDto> carDtos = new HashSet<>();

			for (Car car : owner.getCars()) {
				CarDto carDto = modelMapper.map(car, CarDto.class);
				carDtos.add(carDto);
			}

			return Response.ok(carDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
