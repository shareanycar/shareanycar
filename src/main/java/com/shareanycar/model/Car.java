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

import com.shareanycar.enums.CarStatus;

@Entity
public class Car  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String licensePlateNumber;
	
	@NotNull
	private Integer numberOfSeats;
	
	@NotEmpty
	private String description;
	
	@NotNull
	private Integer mileage;

	@NotNull
	private Double price;

	@NotNull
	private Integer year;
	
	@NotNull
	private CarStatus status;
	
	@NotNull
	private String modelName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insurer_id")
	private Insurer insurer;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "carType_id")
	private CarType carType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fuelType_id")
	private FuelType fuelType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transmissionType_id")
	private TransmissionType transmissionType; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "car")
	private Set<Image> images;

	public Long getId() {
		return id;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public String getDescription() {
		return description;
	}

	public Integer getMileage() {
		return mileage;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getYear() {
		return year;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public Insurer getInsurer() {
		return insurer;
	}

	public CarType getCarType() {
		return carType;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public User getUser() {
		return user;
	}

	public Location getLocation() {
		return location;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}
	
	
		
}
