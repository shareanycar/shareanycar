package com.shareanycar.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.shareanycar.dto.Credentials;
import com.shareanycar.dto.TokenDto;
import com.shareanycar.model.User;
import com.shareanycar.service.AuthService;

@Path("/auth")
public class AuthController {

	@Inject
	public AuthService authService;
	
	@Inject
	public Logger logger;

	@POST
	@Path("/user")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response userAuth(Credentials userCredentials) {
		try {
			User user = authService.authenticateUser(userCredentials.getEmail(), userCredentials.getPassword());
			if (user != null) {
				return Response.ok(new TokenDto(user.getToken())).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
