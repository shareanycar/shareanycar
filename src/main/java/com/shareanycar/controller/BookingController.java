package com.shareanycar.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.shareanycar.annotation.SecuredUser;
import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;
import com.shareanycar.model.User;
import com.shareanycar.service.BookingService;
import com.shareanycar.service.CarAvailabilityService;
import com.shareanycar.service.CarService;
import com.shareanycar.service.MessageService;
import com.shareanycar.service.NotificationService;
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

	@Inject
	public NotificationService notificationService;

	@Inject
	public MessageService messageService;

	@Inject
	public CarAvailabilityService carAvailabilityService;

	@Inject
	public CarService carService;

	@POST
	@Path("/car/{carId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response bookCar(BookingDto bookingDto, @PathParam("carId") Long carId,
			@Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			Booking booking = modelMapper.map(bookingDto, Booking.class);

			if (carAvailabilityService.isAvailable(carId, booking.getDateFrom(), booking.getDateTo())) {
				bookingService.book(booking, user, carId);
				carAvailabilityService.setCarBooked(carId, booking.getDateFrom(), booking.getDateTo());
				notificationService.notifyCarBooked(booking);
				messageService.carBooked(booking);
			}else{
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/car/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response carBookings(@PathParam("carId") Long carId, @Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			List<BookingDto> bookingDtos = new ArrayList<>();
			for (Booking b : bookingService.carBookings(carId, user)) {
				bookingDtos.add(modelMapper.map(b, BookingDto.class));
			}
			return Response.ok(bookingDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/owner")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response ownerBookings(@Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			List<BookingDto> bookingDtos = new ArrayList<>();

			for (Booking b : bookingService.ownerBookings(user)) {
				bookingDtos.add(modelMapper.map(b, BookingDto.class));
			}

			return Response.ok(bookingDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response clientBookings(@Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			List<BookingDto> bookingDtos = new ArrayList<>();
			for (Booking b : bookingService.clientBookings(user)) {
				bookingDtos.add(modelMapper.map(b, BookingDto.class));
			}
			return Response.ok(bookingDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response viewBooking(@PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			BookingDto booking = modelMapper.map(bookingService.viewBooking(id, user), BookingDto.class);
			return Response.ok(booking).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response confirmBooking(@PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			Booking booking = bookingService.findOne(id);
			bookingService.confirmBooking(booking, user);
			notificationService.notifyBookingConfirmed(booking);
			messageService.bookingConfirmed(booking);
			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response cancelBooking(@PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			User user = contextUtil.getCurrentUser(securityContext);
			Booking booking = bookingService.findOne(id);
			bookingService.cancelBooking(booking, user);
			notificationService.notifyBookingCanceled(booking);
			messageService.bookingCanceled(booking);
			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
