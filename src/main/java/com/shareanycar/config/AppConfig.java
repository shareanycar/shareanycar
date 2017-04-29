package com.shareanycar.config;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	final private String serviceUrl = "http://localhost:8080/shareanycar/api/" ;
	final private String corsUrl = "http://localhost:4200" ;
	
	final private String rootLocation = "/Users/slava/Documents/workspace/shareanycar-ui/src/assets/upload/";
	final private String tmpLocation = "/Users/slava/Documents/workspace/tmp/";
	final private String smallLocation = "small/";
	final private String mediumLocation = "medium/";
	final private String largeLocation = "large/";

	final private int smallWidth = 160;
	final private int smallHeight = 120;
	
	final private int mediumWidth = 320;
	final private int mediumHeight = 240;
	
	final private int largeWidth = 640;
	final private int largeHeight = 480;

	final private String urlPrefix = "http://localhost:4200/assets/upload/";
	final private String amazonUrlPrefix = "https://s3-eu-west-1.amazonaws.com/shareanycar-bucket/";
	
	final private String s3BucketName = "shareanycar-bucket"; 
	
	final private boolean local = false;
	
	public String getAmazonUrlPrefix() {
		return amazonUrlPrefix;
	}

	public String getCorsUrl() {
		return corsUrl;
	}

	public String getTmpLocation() {
		return tmpLocation;
	}

	public boolean isLocal() {
		return local;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public String getS3BucketName() {
		return s3BucketName;
	}

	public String getRootLocation() {
		return rootLocation;
	}

	public String getSmallLocation() {
		return smallLocation;
	}

	public String getMediumLocation() {
		return mediumLocation;
	}

	public String getLargeLocation() {
		return largeLocation;
	}
	
	public int getSmallWidth() {
		return smallWidth;
	}

	public int getSmallHeight() {
		return smallHeight;
	}
	
	public int getMediumWidth() {
		return mediumWidth;
	}

	public int getMediumHeight() {
		return mediumHeight;
	}

	public int getLargeWidth() {
		return largeWidth;
	}

	public int getLargeHeight() {
		return largeHeight;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

}
