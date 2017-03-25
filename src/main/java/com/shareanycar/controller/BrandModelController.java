package com.shareanycar.controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.shareanycar.dto.BrandDto;
import com.shareanycar.dto.ModelDto;
import com.shareanycar.model.Brand;
import com.shareanycar.model.Model;
import com.shareanycar.service.BrandService;
import com.shareanycar.service.ModelService;

@Path("/brandmodel")
public class BrandModelController {

	@Inject
	public BrandService brandService;

	@Inject
	public ModelService modelService;

	@Inject
	public Logger logger;

	@GET
	@Path("/brands")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response brands() {
		try {
			List<BrandDto> brands = new LinkedList<>();
			for (Brand brand : brandService.findAll()) {
				brands.add(new BrandDto(brand.getId(), brand.getName()));
			}
			return Response.ok(brands).build();
		} catch (Exception e) {
			logger.error("error fetching brands:" + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/brand/{id}/models")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response models(@PathParam("id") Long id) {
		try {
			List<ModelDto> models = new LinkedList<>();
			for (Model model : modelService.findModelByBrandId(id)) {
				models.add(new ModelDto(model.getId(), model.getName(), model.getBrand().getId(),
						model.getBrand().getName()));
			}
			return Response.ok(models).build();
		} catch (Exception e) {
			logger.error("error fetching models:" + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
		
}
