package com.shareanycar.util;

import java.security.Principal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import com.shareanycar.model.User;
import com.shareanycar.service.UserService;

public class ContextUtil {
	

	@Inject
	public UserService userService;

	

	public User getCurrentUser(SecurityContext securityContext) throws Exception {
		Principal principal = securityContext.getUserPrincipal();
		User user = userService.findByEmail(principal.getName());

		if (user == null) {
			throw new Exception("can not find current user");
		}

		return user;
	}
}
