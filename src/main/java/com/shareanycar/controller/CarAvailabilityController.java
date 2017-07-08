package com.shareanycar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import com.shareanycar.annotation.SecuredUser;
import com.shareanycar.dto.CarAvailabilityDto;
import com.shareanycar.model.CarAvailability;
import com.shareanycar.model.User;
import com.shareanycar.service.CarAvailabilityService;
import com.shareanycar.service.CarService;
import com.shareanycar.util.ContextUtil;
import com.shareanycar.util.MiscUtils;

@Path("/availability")
public class CarAvailabilityController {

	@Inject
	public Logger logger;

	@Inject
	public CarAvailabilityService carAvailabilityService;

	@Inject
	public ModelMapper modelMapper;

	@Inject
	public MiscUtils miscUtils;

	@Inject
	public ContextUtil contextUtil;

	@Inject
	public CarService carService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{carId}/{fromDate}/{toDate}")
	public Response availability(@PathParam("carId") Long carId, @PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {

		try {
			
			List<CarAvailabilityDto> carAvailability = new ArrayList<>();
			for (CarAvailability a : carAvailabilityService.getAvailability(carId, miscUtils.String2LocalDate(fromDate),
					miscUtils.String2LocalDate(toDate))) {
				carAvailability.add(modelMapper.map(a, CarAvailabilityDto.class));
			}
			
			return Response.ok(carAvailability).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/{carId}/{fromDate}/{toDate}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response setAvailable(@PathParam("carId") Long carId, @PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate, @Context SecurityContext securityContext) {

		try {
			User user = contextUtil.getCurrentUser(securityContext);
			carAvailabilityService.setCarAvailable(user, carId, miscUtils.String2LocalDate(fromDate),
					miscUtils.String2LocalDate(toDate));
			return Response.ok().build();

		} catch (

		Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("/{carId}/{fromDate}/{toDate}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response setUnavailable(@PathParam("carId") Long carId, @PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate, @Context SecurityContext securityContext) {

		try {
			User user = contextUtil.getCurrentUser(securityContext);
			carAvailabilityService.setCarUnavailable(user, carId, miscUtils.String2LocalDate(fromDate),
					miscUtils.String2LocalDate(toDate));
			return Response.ok().build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
