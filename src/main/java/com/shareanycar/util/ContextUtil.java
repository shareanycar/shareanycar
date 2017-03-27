package com.shareanycar.util;

import java.security.Principal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import com.shareanycar.model.Owner;
import com.shareanycar.service.OwnerService;

public class ContextUtil {
	@Inject
	public OwnerService ownerService;
	
	public Owner getCurrentOwner(SecurityContext securityContext) throws Exception{
		Principal principal = securityContext.getUserPrincipal();
		Owner owner = ownerService.findOwnerByEmail(principal.getName());
		if(owner == null){
			throw new Exception("can not find current owner");
		}
		return owner;
	}
}
