package com.shareanycar.config;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	final private String imageLocation = "/Users/slava/Documents/workspace/shareanycar-ui/src/assets/upload/";
	final private String small = "small/";
	final private String medium = "medium/";
	final private String large = "large/";

	final private int smallWidth = 160;
	final private int smallHeight = 120;
	
	final private int mediumWidth = 320;
	final private int mediumHeight = 240;
	
	final private int largeWidth = 640;
	final private int largeHeight = 480;

	final private String urlPrefix = "http://localhost:4200/assets/upload/";
	
	public String getImageLocation() {
		return imageLocation;
	}

	public String getImageLocationSmall() {
		return imageLocation + small ;
	}

	public String getImageLocationLarge() {
		return imageLocation + large ;
	}

	public String getImageLocationMedium() {
		return imageLocation + medium ;
	}
	
	public String getUrlPrefixSmall() {
		return urlPrefix + small ;
	}

	public String getUrlPrefixLarge() {
		return urlPrefix + large;
	}

	public String getUrlPrefixMedium() {
		return urlPrefix + medium ;
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

}
