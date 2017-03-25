package com.shareanycar.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.shareanycar.dto.Credentials;
import com.shareanycar.dto.TokenDto;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Owner;
import com.shareanycar.service.AuthService;

@Path("/auth")
public class AuthController {

	@Inject
	public AuthService authService;
	
	@POST @Path("/owner")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response ownerAuth(Credentials ownerCredentials ){

		Owner owner = authService.authenticateOwner(ownerCredentials.getEmail(), ownerCredentials.getPassword());
		if(owner != null ){
			return Response.ok(new TokenDto(owner.getToken())).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
		
	@POST @Path("/customer")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response customerAuth(Credentials customerCredentials){
		Customer customer = authService.authenticateCustomer(customerCredentials.getEmail(), customerCredentials.getPassword());
		
		if( customer != null ){
			return Response.ok(new TokenDto(customer.getToken())).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}	
}
