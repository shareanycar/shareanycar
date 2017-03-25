package com.shareanycar.controller;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shareanycar.dto.CustomerDto;
import com.shareanycar.model.Customer;
import com.shareanycar.service.CustomerService;

/*
 * post /api/customer/signup
 * get /api/customer/{id}
 */

@Path("/customer")
public class CustomerController {
	
	@Inject
	public CustomerService customerService;

	@POST @Path("/signup")
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public  CustomerDto customerSignUp(CustomerDto customerDto) {
		Customer cust = customerService.createCustomer(customerDto.getFirstName(), customerDto.getLastName(),
				customerDto.getPhone(), customerDto.getEmail(), customerDto.getPassword());

		return new CustomerDto(cust.getId(), cust.getFirstName(), cust.getLastName(),
				cust.getEmail(), cust.getPhone());
	}

	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public  CustomerDto viewCustomerDetails(@PathParam("id") Long id) {
		Customer cust = customerService.find(id);
		
		return new CustomerDto(cust.getId(), cust.getFirstName(), cust.getLastName(),
				cust.getEmail(), cust.getPhone());
	}

}
