package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
	private Long id;
	private Long carId;
	private String fileName;
	private String urlSmall;
	private String urlLarge;
	private String urlOrig;
	
	public ImageDto(){}

	public ImageDto(Long id, Long carId, String fileName, String urlSmall, String urlLarge, String urlOrig) {
		super();
		this.id = id;
		this.carId = carId;
		this.fileName = fileName;
		this.urlSmall = urlSmall;
		this.urlLarge = urlLarge;
		this.urlOrig = urlOrig;
	}

	public Long getId() {
		return id;
	}

	public Long getCarId() {
		return carId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getUrlSmall() {
		return urlSmall;
	}

	public String getUrlLarge() {
		return urlLarge;
	}

	public String getUrlOrig() {
		return urlOrig;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUrlSmall(String urlSmall) {
		this.urlSmall = urlSmall;
	}

	public void setUrlLarge(String urlLarge) {
		this.urlLarge = urlLarge;
	}

	public void setUrlOrig(String urlOrig) {
		this.urlOrig = urlOrig;
	}

	
	
	
	
}
