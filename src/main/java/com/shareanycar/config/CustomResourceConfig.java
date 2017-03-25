package com.shareanycar.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class CustomResourceConfig extends ResourceConfig{
	public CustomResourceConfig(){
		register(new CustomBinder());
		register(JacksonFeature.class);
		register(Filter.class);
		packages(true, "com.shareanycar");	
	}
	
}
