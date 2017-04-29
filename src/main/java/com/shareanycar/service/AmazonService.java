package com.shareanycar.service;

import java.io.File;

import javax.inject.Inject;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.shareanycar.config.AppConfig;

public class AmazonService {

	@Inject
	public AmazonS3 s3;

	@Inject
	public AppConfig appConfig;

	public void uploadFile(String key, File file) {
		s3.putObject(appConfig.getS3BucketName(), key, file);
		s3.setObjectAcl(appConfig.getS3BucketName(), key, CannedAccessControlList.PublicRead);
	}
	
	public void removeFile(String key){
		s3.deleteObject(appConfig.getS3BucketName(), key);
	}

	public static void main(String[] args) {
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIEN4MG53ZMCZHWBA",
				"ZB6L3RE6WEDy6hMMxPeRPYGkUBbdR/FuvLYpjQ23");
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
				.withRegion(Regions.EU_WEST_1).build();

		File file = new File("/Users/slava/Documents/images2.jpg");
		s3.putObject("shareanycar-bucket", "large/test", file);
		s3.setObjectAcl("shareanycar-bucket", "large/test", CannedAccessControlList.PublicRead);

	}
}
