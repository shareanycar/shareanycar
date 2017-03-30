package com.shareanycar.config;

import org.jvnet.hk2.annotations.Service;

@Service
public class AppConfig {
	final private String imageLocation = "/Users/slava/Documents/workspace/shareanycar-ui/src/assets/upload/";
	final private String small = "small/";
	final private String orig = "orig/";
	final private String large = "large/";

	final private int smallWidth = 320;
	final private int smallHeight = 240;
	final private int largeWidth = 640;
	final private int largeHeight = 480;

	final private String urlPrefix = "http://localhost:4200/assets/upload/";

	public String getImageLocationSmall() {
		return imageLocation + small ;
	}

	public String getImageLocationLarge() {
		return imageLocation + large ;
	}

	public String getImageLocationOrig() {
		return imageLocation + orig ;
	}
	
	public String getUrlPrefixSmall() {
		return urlPrefix + small ;
	}

	public String getUrlPrefixLarge() {
		return urlPrefix + large;
	}

	public String getUrlPrefixOrig() {
		return urlPrefix + orig ;
	}

	public int getSmallWidth() {
		return smallWidth;
	}

	public int getSmallHeight() {
		return smallHeight;
	}

	public int getLargeWidth() {
		return largeWidth;
	}

	public int getLargeHeight() {
		return largeHeight;
	}

}
