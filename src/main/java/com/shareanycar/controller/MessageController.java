package com.shareanycar.controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.shareanycar.dto.MessageDto;
import com.shareanycar.dto.MessageInfoDto;
import com.shareanycar.model.Message;
import com.shareanycar.model.User;
import com.shareanycar.service.MessageService;
import com.shareanycar.service.UserService;
import com.shareanycar.util.ContextUtil;

@Path("/message")
public class MessageController {

	@Inject
	public UserService userService;

	@Inject
	public MessageService messageService;

	@Inject
	public ModelMapper modelMapper;

	@Inject
	public ContextUtil context;

	@Inject
	public Logger logger;

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response send(MessageDto messageDto, @Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			Message message = modelMapper.map(messageDto, Message.class);
			messageService.send(user, message);

			return Response.ok().build();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/read/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredUser
	public Response read(@PathParam("id") Long id, @Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			Message message = messageService.read(user, id);
			MessageDto m = modelMapper.map(message, MessageDto.class);
			return Response.ok(m).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/incoming")
	@SecuredUser
	public Response incoming(@Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			List<Message> msgs = messageService.incoming(user.getId());
			List<MessageInfoDto> msgInfoDtos = new LinkedList<>();
			for (Message m : msgs) {
				MessageInfoDto dto = modelMapper.map(m, MessageInfoDto.class);
				msgInfoDtos.add(dto);
			}

			return Response.ok(msgInfoDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/outgoing")
	@SecuredUser
	public Response outgoing(@Context SecurityContext securityContext) {
		try {
			User user = context.getCurrentUser(securityContext);
			List<Message> msgs = messageService.outgoing(user.getId());
			List<MessageInfoDto> msgInfoDtos = new LinkedList<>();
			for (Message m : msgs) {
				MessageInfoDto dto = modelMapper.map(m, MessageInfoDto.class);
				msgInfoDtos.add(dto);
			}
			return Response.ok(msgInfoDtos).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
