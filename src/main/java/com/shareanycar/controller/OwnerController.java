package com.shareanycar.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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

import org.slf4j.Logger;

import com.shareanycar.annotation.SecuredOwner;
import com.shareanycar.dto.OwnerDto;
import com.shareanycar.dto.PasswordDto;
import com.shareanycar.model.Owner;
import com.shareanycar.service.CarService;
import com.shareanycar.service.OwnerService;
/*
 * services :
 * post /api/owner
 * get /api/owner
 * put /api/owner
 * put /api/owner/password
 * get /api/owner/country/{country}/city/{city}
 * get /api/owner/country/{country}
 * get /api/owner/city/{city}
 */
import com.shareanycar.util.ContextUtil;

@Path("/owner")
public class OwnerController {

	@Inject
	public OwnerService ownerService ;

	@Inject
	public CarService carService;
	
	@Inject
	public ContextUtil context;
	
	@Inject
	public Logger logger ;
	

	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public  Response create(OwnerDto ownerDto) {

		try{
			ownerService.create(ownerDto.getFirstName(), ownerDto.getLastName(), ownerDto.getCountry(),
				ownerDto.getCity(), ownerDto.getPhone(), ownerDto.getEmail(), ownerDto.getPassword());
		
			return Response.ok().build();
		}catch(Exception e){
			logger.error("error creating owner:" + ownerDto);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		 
		
	}
	
	@GET 
	@Produces({ MediaType.APPLICATION_JSON})
	@SecuredOwner
	public Response details(@Context SecurityContext securityContext) {
		try{
			Owner owner = context.getCurrentOwner(securityContext);
			OwnerDto ownerDto = new OwnerDto(owner.getId(), owner.getFirstName(), owner.getLastName(),
					owner.getLocation().getCountry(), owner.getLocation().getCity(), owner.getPhone(), owner.getEmail());
			return Response.ok(ownerDto).build();
		}catch(Exception e){
			logger.error("error getting owner details");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		 

	}

	@PUT 
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@SecuredOwner
	public  Response update(OwnerDto ownerDto, @Context SecurityContext securityContext) {
		try{
			Owner owner = context.getCurrentOwner(securityContext);
			ownerService.update(owner.getId(), ownerDto.getFirstName(), ownerDto.getLastName(), ownerDto.getCountry(), ownerDto.getCity(), ownerDto.getPhone());
			return Response.ok().build();
		}catch(Exception e){
			logger.error("error updating owner:" + ownerDto);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
				
	}

	@PUT @Path("/password")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@SecuredOwner
	public  Response updatePassword(PasswordDto passwordDto, @Context SecurityContext securityContext) {
		try{									  
			Owner owner = context.getCurrentOwner(securityContext);
			ownerService.updatePassword(owner.getId(), passwordDto.getOldPassword(), passwordDto.getNewPassword());
			return Response.ok().build();
		}catch(Exception e){
			logger.error("error updating password:" + passwordDto);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	@GET @Path("/country/{country}/city/{city}")
	@Produces({MediaType.APPLICATION_JSON})
	public  Set<OwnerDto> ownersInLocation(@PathParam("country") String country, @PathParam("city") String city) {
		Set<OwnerDto> owners = new HashSet<>();
		for (Owner o : ownerService.findOwnersByCountryAndCity(country, city)) {
			owners.add(new OwnerDto(o.getId(), o.getFirstName(), o.getLastName(), o.getLocation().getCountry(),
					o.getLocation().getCity(), o.getPhone(), o.getEmail()));
		}
		return owners;
	}

	@GET @Path( "/country/{country}")
	@Produces({MediaType.APPLICATION_JSON})
	public  Set<OwnerDto> ownersInCountry(@PathParam("country") String country) {
		Set<OwnerDto> owners = new HashSet<>();
		for (Owner o : ownerService.findOwnersByCountry(country)) {
			owners.add(new OwnerDto(o.getId(), o.getFirstName(), o.getLastName(), o.getLocation().getCountry(),
					o.getLocation().getCity(), o.getPhone(), o.getEmail()));
		}
		return owners;
	}

	@GET @Path("/city/{city}")
	@Produces({MediaType.APPLICATION_JSON})
	public  Set<OwnerDto> ownersInCity(@PathParam("city") String city) {
		Set<OwnerDto> owners = new HashSet<>();
		for (Owner o : ownerService.findOwnersByCity(city)) {
			owners.add(new OwnerDto(o.getId(), o.getFirstName(), o.getLastName(), o.getLocation().getCountry(),
					o.getLocation().getCity(), o.getPhone(), o.getEmail()));
		}
		return owners;
	}

}
