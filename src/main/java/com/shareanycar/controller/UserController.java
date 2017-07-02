package com.shareanycar.controller;

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
import com.shareanycar.dto.PasswordDto;
import com.shareanycar.dto.UserDto;
import com.shareanycar.dto.UserInfoDto;
import com.shareanycar.model.User;
import com.shareanycar.service.NotificationService;
import com.shareanycar.service.UserService;
import com.shareanycar.util.ContextUtil;

@Path("/user")
public class UserController {
	@Inject
	public ContextUtil context;

	@Inject
	public Logger logger;
	
	@Inject
	public NotificationService notificationService;
	
	@Inject
	public ModelMapper modelMapper;

	@Inject
	public UserService userService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response create(UserDto userDto) {

		try {
			User user = modelMapper.map(userDto, User.class);

			Long id = userService.create(user);
			notificationService.notifyActivateAccount(id);
			
			return Response.ok().build();
		} catch (Exception e) {
			logger.error("error creating user:" + userDto + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@SecuredUser
	public Response delete(@Context SecurityContext securityContext){
		try{
			User user = context.getCurrentUser(securityContext);
			String email = user.getEmail();
			userService.delete(user);
			notificationService.notifyAccountRemoved(email);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("error removing user");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@SecuredUser
	public Response details(@Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			UserDto userDto = modelMapper.map(user, UserDto.class);
			userDto.setPassword(null);// hide sensitive field

			return Response.ok(userDto).build();
		} catch (Exception e) {
			logger.error("error getting user details:" + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("{id}/info")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response info(@PathParam("id") Long id) {
		try {
			User user = userService.findById(id);
			
			UserInfoDto userInfo = modelMapper.map(user, UserInfoDto.class);
			
			return Response.ok(userInfo).build();
		} catch (Exception e) {
			logger.error("error getting user info:" + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredUser
	public Response update(UserDto userDto, @Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);

			User updateUser = modelMapper.map(userDto, User.class);

			userService.update(user.getId(), updateUser);

			return Response.ok().build();
		} catch (Exception e) {
			logger.error("error updating owner:" + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/password")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@SecuredUser
	public Response updatePassword(PasswordDto passwordDto, @Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			userService.updatePassword(user.getId(), passwordDto.getOldPassword(), passwordDto.getNewPassword());
			return Response.ok().build();
		} catch (Exception e) {
			logger.error("error updating password:" + passwordDto + " " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/activate/{token}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response activate(@PathParam("token") String activationToken){
		try{
			Long id = userService.activate(activationToken);
			notificationService.notifyAccountActivated(id);
			
			return Response.ok().build();
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
}
