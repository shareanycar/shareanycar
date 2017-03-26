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
import org.slf4j.Logger;

import com.shareanycar.annotation.SecuredOwner;
import com.shareanycar.dto.ImageDto;
import com.shareanycar.model.Image;
import com.shareanycar.model.Owner;
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
	
	@GET
	@Path("car/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response carImages(@PathParam("id") Long id){
		try{
			List<ImageDto> images = new LinkedList<>();
			
			for(Image image : imageService.findImageByCarId(id)){
				images.add(new ImageDto(image.getId(), image.getName(), image.getUrl()));
			}
			return Response.ok(images).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("car/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredOwner
	public Response uploadCarImage(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("id") Long id, @Context SecurityContext securityContext) {
		
		try{
			Owner owner = contextUtil.getCurrentOwner(securityContext);
			imageService.uploadImage(id,owner.getId(), is);
			return Response.ok().build();
			
		}catch(Exception e){
			logger.error("can not upload image for car with id:" + id + "; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredOwner
	public Response deleteCarImage(@PathParam("id") Long id, @Context SecurityContext securityContext){
		try{
			
			Owner owner = contextUtil.getCurrentOwner(securityContext);
			
			if(!imageService.deleteImage(id,owner.getId())){
				throw new Exception("can not delete image");
			}
						return Response.ok().build();
		}catch(Exception e){
			logger.error("can not delete image for car with id:" + id +"; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
}
