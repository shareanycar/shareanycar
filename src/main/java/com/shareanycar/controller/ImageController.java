package com.shareanycar.controller;

import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;

import com.shareanycar.service.ImageService;

@Path("image")
public class ImageController {

	@Inject
	public ImageService imageService;

	@Inject
	public Logger logger;
	
	@POST
	@Path("car/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadCarImage(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("id") Long id) {
		try{
			imageService.uploadImage(id, is);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("can not upload image for car with id:" + id + "; " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
}
