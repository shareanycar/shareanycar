package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
	private Long id;
	private Long carId;
	private String fileName;
	private String url;
	
	public ImageDto(){}

	public ImageDto(Long id, Long carId, String fileName, String url) {
		super();
		this.id = id;
		this.carId = carId;
		this.fileName = fileName;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	
	
	
	
}
