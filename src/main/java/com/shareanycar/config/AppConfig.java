package com.shareanycar.config;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	
	@Inject
	public ConfigReader configReader ;
	
	public String getAmazonKey(){
		return configReader.getProperty("amazon.key");
	}
	
	public String getAmazonSecret(){
		return configReader.getProperty("amazon.secret");
	}
	
	public String getAmazonUrlPrefix() {
		return configReader.getProperty("amazon.url.prefix");
	}

	public String getCorsUrl() {
		return configReader.getProperty("service.url.cors");
	}

	public String getTmpLocation() {
		return configReader.getProperty("image.tmp.location");
	}

	public boolean isLocal() {
		return Boolean.valueOf(configReader.getProperty("service.local"));
		
	}

	public String getServiceUrl() {
		return configReader.getProperty("service.url");
	}

	public String getS3BucketName() {
		return configReader.getProperty("amazon.bucket.name");
	}

	public String getRootLocation() {
		return configReader.getProperty("image.root.location");
	}

	public String getSmallLocation() {
		return configReader.getProperty("image.small.location");
	}

	public String getMediumLocation() {
		return configReader.getProperty("image.medium.location");
	}

	public String getLargeLocation() {
		return configReader.getProperty("image.large.location");
	}
	
	public int getSmallWidth() {
		return Integer.valueOf(configReader.getProperty("image.small.width"));
	}

	public int getSmallHeight() {
		return Integer.valueOf(configReader.getProperty("image.small.height"));
	}
	
	public int getMediumWidth() {
		return Integer.valueOf(configReader.getProperty("image.medium.width"));
	}

	public int getMediumHeight() {
		return Integer.valueOf(configReader.getProperty("image.medium.height"));
	}

	public int getLargeWidth() {
		return Integer.valueOf(configReader.getProperty("image.large.width"));
	}

	public int getLargeHeight() {
		return Integer.valueOf(configReader.getProperty("image.large.height"));
	}

	public String getUrlPrefix() {
		return configReader.getProperty("image.url.prefix");
	}

}
