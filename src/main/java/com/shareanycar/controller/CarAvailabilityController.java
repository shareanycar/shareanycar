package com.shareanycar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import com.shareanycar.dto.CarAvailabilityDto;
import com.shareanycar.model.CarAvailability;
import com.shareanycar.service.CarAvailabilityService;
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

	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response availability(@PathParam("carId") Long carId) {
		try {
			List<CarAvailabilityDto> carAvailability = new ArrayList<>();
			
			for (CarAvailability a : carAvailabilityService.getAvailability(carId)) {
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
	public Response setAvailable(@PathParam("carId") Long carId, @PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {

		try {
			carAvailabilityService.setCarAvailable(carId, miscUtils.String2LocalDate(fromDate),
					miscUtils.String2LocalDate(toDate));

			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("/{carId}/{fromDate}/{toDate}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setUnavailable(@PathParam("carId") Long carId, @PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {

		try {
			carAvailabilityService.setCarUnavailable(carId, miscUtils.String2LocalDate(fromDate),
					miscUtils.String2LocalDate(toDate));

			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
