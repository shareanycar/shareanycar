package com.shareanycar.providers;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.shareanycar.config.AppConfig;

public class FactoryAmazonS3 implements Factory<AmazonS3> {

	@Inject
	public AppConfig appConfig;
	@Override
	public void dispose(AmazonS3 arg0) {
	}

	@Override
	public AmazonS3 provide() {
		BasicAWSCredentials creds = new BasicAWSCredentials(appConfig.getAmazonKey(), appConfig.getAmazonSecret());
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.EU_WEST_1).build();
		return s3;
	}

}
