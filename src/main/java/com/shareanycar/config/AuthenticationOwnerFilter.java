package com.shareanycar.config;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.shareanycar.annotation.SecuredOwner;
import com.shareanycar.model.Owner;
import com.shareanycar.service.AuthService;

//Authorization: Bearer <token-goes-here>

@SecuredOwner
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationOwnerFilter implements ContainerRequestFilter {

	@Inject
	AuthService authService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Validate the token
            Owner owner = authService.authenticateOwner(token);
            if(owner == null){
            	throw new Exception();
            }
            setSecurityContext(requestContext, owner);
        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
    private void setSecurityContext(ContainerRequestContext requestContext, final Owner owner){
    	requestContext.setSecurityContext(new SecurityContext() {

    	    @Override
    	    public Principal getUserPrincipal() {

    	        return new Principal() {
	            
					@Override
					public String getName() {
						return owner.getEmail();
					}
    	        };
    	    }

			@Override
			public boolean isUserInRole(String role) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getAuthenticationScheme() {
				// TODO Auto-generated method stub
				return null;
			}
 	   
    	});
    }
    
}
