package com.shareanycar.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String urlSmall;
	
	@NotEmpty
	private String urlMedium;
	
	@NotEmpty
	private String urlLarge;
	
	private boolean main;

	public Long getId() {
		return id;
	}

	public Car getCar() {
		return car;
	}

	public String getName() {
		return name;
	}

	public String getUrlSmall() {
		return urlSmall;
	}

	public String getUrlMedium() {
		return urlMedium;
	}

	public String getUrlLarge() {
		return urlLarge;
	}

	public boolean isMain() {
		return main;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrlSmall(String urlSmall) {
		this.urlSmall = urlSmall;
	}

	public void setUrlMedium(String urlMedium) {
		this.urlMedium = urlMedium;
	}

	public void setUrlLarge(String urlLarge) {
		this.urlLarge = urlLarge;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	
}
