package com.shareanycar.controller;

import java.io.InputStream;
import java.util.LinkedList;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import com.shareanycar.annotation.SecuredUser;
import com.shareanycar.dto.ImageDto;
import com.shareanycar.model.Image;
import com.shareanycar.model.User;
import com.shareanycar.service.ImageService;
import com.shareanycar.util.ContextUtil;

@Path("image")
public class ImageController {

	@Inject
	public ImageService imageService;

	@Inject
	public ContextUtil contextUtil;

	@Inject
	public Logger logger;
	
	@Inject
	public ModelMapper modelMapper;
	
	@GET
	@Path("car/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response carImages(@PathParam("id") Long id) {
		try {
			List<ImageDto> images = new LinkedList<>();

			for (Image image : imageService.findNotMainByCarId(id)) {
				ImageDto imageDto = modelMapper.map(image, ImageDto.class);
				images.add(imageDto);
			}
			return Response.ok(images).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("car/{id}/main")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response createMainCarImage(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("id") Long id,
			@Context SecurityContext securityContext) {

		try {
			User user = contextUtil.getCurrentUser(securityContext);
			imageService.uploadCarImage(id, user.getId(), is, true);
			return Response.ok().build();

		} catch (Exception e) {
			logger.error("can not upload image for car with id:" + id + "; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("car/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response createCarImage(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("id") Long id,
			@Context SecurityContext securityContext) {

		try {
			User user = contextUtil.getCurrentUser(securityContext);
			imageService.uploadCarImage(id, user.getId(), is, false);
			return Response.ok().build();

		} catch (Exception e) {
			logger.error("can not upload image for car with id:" + id + "; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	
	@GET
	@Path("/{imageId}/car/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response detailCarImage(@PathParam("carId") Long carId, @PathParam("imageId") Long imageId){
		try{
			Image image = imageService.findById(imageId);
			if(carId != image.getCar().getId()){
				throw new Exception("image with id:" + imageId + " does not belong to car with id:" + carId);
			}
			ImageDto imageDto = modelMapper.map(image, ImageDto.class);
			return Response.ok(imageDto).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@Path("/{imageId}/car/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response deleteCarImage(@PathParam("carId") Long carId, @PathParam("imageId") Long imageId,
			@Context SecurityContext securityContext) {
		try {

			User user = contextUtil.getCurrentUser(securityContext);

			imageService.deleteCarImage(imageId,carId, user.getId(), true);
			
			return Response.ok().build();
		} catch (Exception e) {
			logger.error("can not delete image for car with id:" + imageId + "; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("user")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response createUserImage(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetail, 
			@Context SecurityContext securityContext) {

		try {
			User user = contextUtil.getCurrentUser(securityContext);
			imageService.uploadUserImage(user.getId(), is);
			return Response.ok().build();

		} catch (Exception e) {
			logger.error("can not upload image for user " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response deleteUserImage(@Context SecurityContext securityContext){
		try{
			User user = contextUtil.getCurrentUser(securityContext);
			imageService.deleteUserImage(user.getId(),true);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("can not delete image for user " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	

}
