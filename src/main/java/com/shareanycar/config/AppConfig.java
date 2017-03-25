package com.shareanycar.config;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	private String imageLocation = "/Users/slava/Documents/workspace/upload/";
	private String urlPrefix = "http://localhost:8080/imgs/";
	
	public String getImageLocation(){
		return imageLocation;
	}
	
	public String getUrlPrefix(){
		return urlPrefix;
	}
}
