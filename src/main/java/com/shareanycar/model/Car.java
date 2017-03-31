package com.shareanycar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Car  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;
	
	private Integer mileage;
	
	private String features;
	
	private String fuelType;
	
	private Double price;

	@NotNull
	private Integer year;
	
	@NotNull
	private boolean status;
	
	private String transmissionType; // mechanic, automatic
	
	private String carType; // sedan, suv, minivan
	
	@NotNull
	private Integer numberOfSeats;
	
	private String defaultImageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "car")
	private Set<Image> images;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "car")
	private Set<Booking> bookings;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "car")
	private Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getDefaultImageUrl() {
		return defaultImageUrl;
	}

	public void setDefaultImageUrl(String defaultImageUrl) {
		this.defaultImageUrl = defaultImageUrl;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public String getCarType() {
		return carType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Integer getMileage() {
		return mileage;
	}

	public String getFeatures() {
		return features;
	}

	public String getFuelType() {
		return fuelType;
	}

	public Double getPrice() {
		return price;
	}

	public void setMileage(Integer milage) {
		this.mileage = milage;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public static class Builder{
		
		private String name;
		private String description;
		private Integer mileage;
		private String features;
		private String fuelType;
		private Double price;
		private Integer year;
		private boolean status;
		private String transmissionType; 
		private String carType; 
		private Integer numberOfSeats;
		private String defaultImageUrl;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder setMileage(Integer mileage) {
			this.mileage = mileage;
			return this;
		}
		public Builder setFeatures(String features) {
			this.features = features;
			return this;
		}
		public Builder setFuelType(String fuelType) {
			this.fuelType = fuelType;
			return this;
		}
		public Builder setPrice(Double price) {
			this.price = price;
			return this;
		}
		public Builder setYear(Integer year) {
			this.year = year;
			return this;
		}
		public Builder setStatus(boolean status) {
			this.status = status;
			return this;
		}
		public Builder setTransmissionType(String transmissionType) {
			this.transmissionType = transmissionType;
			return this;
		}
		public Builder setCarType(String carType) {
			this.carType = carType;
			return this;
		}
		public Builder setNumberOfSeats(Integer numberOfSeats) {
			this.numberOfSeats = numberOfSeats;
			return this;
		}
		public Builder setDefaultImageUrl(String defaultImageUrl) {
			this.defaultImageUrl = defaultImageUrl;
			return this;
		}
		
		public Car build(){
			Car car = new Car();
			
			car.carType = this.carType;
			car.defaultImageUrl = this.defaultImageUrl;
			car.description = this.description;
			car.features = this.features;
			car.fuelType = this.fuelType;
			car.name = this.name;
			car.mileage = this.mileage;
			car.numberOfSeats = this.numberOfSeats;
			car.price = this.price;
			car.transmissionType = this.transmissionType;
			car.year = this.year;
			car.status = this.status;
			
			return car;
		}
		
	}
}
