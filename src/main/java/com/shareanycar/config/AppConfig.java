package com.shareanycar.config;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	private String imageLocation = "/Users/slava/Documents/workspace/shareanycar-ui/src/assets/upload/";
	private String urlPrefix = "http://localhost:4200/assets/upload/";
	
	public String getImageLocation(){
		return imageLocation;
	}
	
	public String getUrlPrefix(){
		return urlPrefix;
	}
}
