package com.shareanycar.controller;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;
import com.shareanycar.model.User;
import com.shareanycar.service.BookingService;
import com.shareanycar.util.ContextUtil;

@Path("/book")
public class BookingController {
	
	@Inject
	public Logger logger;
	
	@Inject
	public BookingService bookingService;
	
	@Inject
	public ModelMapper modelMapper;
	
	@Inject
	public ContextUtil contextUtil;
	
	@POST
	@Path("/{carId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response bookCar(BookingDto bookingDto, @PathParam("carId") Long carId, @Context SecurityContext securityContext){
		try{
			User user = contextUtil.getCurrentUser(securityContext);
			Booking booking = modelMapper.map(bookingDto, Booking.class);
			bookingService.book(booking, user, carId);
			return Response.ok().build();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
		
	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response carBookings(@PathParam("carId") Long carId, @Context SecurityContext securityContext){
		try{
			User user = contextUtil.getCurrentUser(securityContext);
			List<Booking> bookings = bookingService.carBookings(carId, user);
			return Response.ok(bookings).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/owner")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response ownerBookings(@PathParam("carId") Long carId, @Context SecurityContext securityContext){
		try{
			User user = contextUtil.getCurrentUser(securityContext);
			List<Booking> bookings = bookingService.ownerBookings(user);
			return Response.ok(bookings).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response clientBookings(@PathParam("carId") Long carId, @Context SecurityContext securityContext){
		try{
			User user = contextUtil.getCurrentUser(securityContext);
			List<Booking> bookings = bookingService.clientBookings(user);
			return Response.ok(bookings).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
