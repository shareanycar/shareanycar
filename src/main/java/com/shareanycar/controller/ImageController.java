package com.shareanycar.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("image")
public class ImageController {

	@POST
	@Path("car/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadCarImage(@FormDataParam("file") InputStream uploadedInputStream) {
		
		return Response.ok().build();
	}
}
