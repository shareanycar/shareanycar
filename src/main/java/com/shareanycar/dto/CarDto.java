package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

	private Long id;
	private String description;
	private int year;
	private String transmissionTypeName;
	private String fuelTypeName;
	private String carTypeName;
	private String licensePlateNumber;
	private String manufacturerName;
	private String insurerName;
	private String modelName;
	private int numberOfSeats;
	private String features;
	private int mileage;
	private int price;
	private String locationCountry;
	private String locationCity;
	private String imageUrlSmall;
	private String imageUrlMedium;
	private String imageUrlLarge;
	private Long userId;
	private String userFirstName;
	private String userImageUrlSmall;
	private String userImageUrlMedium;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getYear() {
		return year;
	}

	public String getTransmissionTypeName() {
		return transmissionTypeName;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public String getFeatures() {
		return features;
	}

	public int getMileage() {
		return mileage;
	}

	public int getPrice() {
		return price;
	}

	public String getLocationCountry() {
		return locationCountry;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setTransmissionTypeName(String transmissionTypeName) {
		this.transmissionTypeName = transmissionTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getImageUrlSmall() {
		return imageUrlSmall;
	}

	public String getImageUrlMedium() {
		return imageUrlMedium;
	}

	public String getImageUrlLarge() {
		return imageUrlLarge;
	}

	public void setImageUrlSmall(String imageUrlSmall) {
		this.imageUrlSmall = imageUrlSmall;
	}

	public void setImageUrlMedium(String imageUrlMedium) {
		this.imageUrlMedium = imageUrlMedium;
	}

	public void setImageUrlLarge(String imageUrlLarge) {
		this.imageUrlLarge = imageUrlLarge;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserImageUrlSmall() {
		return userImageUrlSmall;
	}

	public String getUserImageUrlMedium() {
		return userImageUrlMedium;
	}

	public void setUserImageUrlSmall(String userImageUrlSmall) {
		this.userImageUrlSmall = userImageUrlSmall;
	}

	public void setUserImageUrlMedium(String userImageUrlMedium) {
		this.userImageUrlMedium = userImageUrlMedium;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carTypeName == null) ? 0 : carTypeName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((fuelTypeName == null) ? 0 : fuelTypeName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageUrlLarge == null) ? 0 : imageUrlLarge.hashCode());
		result = prime * result + ((imageUrlMedium == null) ? 0 : imageUrlMedium.hashCode());
		result = prime * result + ((imageUrlSmall == null) ? 0 : imageUrlSmall.hashCode());
		result = prime * result + ((insurerName == null) ? 0 : insurerName.hashCode());
		result = prime * result + ((licensePlateNumber == null) ? 0 : licensePlateNumber.hashCode());
		result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
		result = prime * result + ((locationCountry == null) ? 0 : locationCountry.hashCode());
		result = prime * result + ((manufacturerName == null) ? 0 : manufacturerName.hashCode());
		result = prime * result + mileage;
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + numberOfSeats;
		result = prime * result + price;
		result = prime * result + ((transmissionTypeName == null) ? 0 : transmissionTypeName.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userImageUrlMedium == null) ? 0 : userImageUrlMedium.hashCode());
		result = prime * result + ((userImageUrlSmall == null) ? 0 : userImageUrlSmall.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDto other = (CarDto) obj;
		if (carTypeName == null) {
			if (other.carTypeName != null)
				return false;
		} else if (!carTypeName.equals(other.carTypeName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (fuelTypeName == null) {
			if (other.fuelTypeName != null)
				return false;
		} else if (!fuelTypeName.equals(other.fuelTypeName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageUrlLarge == null) {
			if (other.imageUrlLarge != null)
				return false;
		} else if (!imageUrlLarge.equals(other.imageUrlLarge))
			return false;
		if (imageUrlMedium == null) {
			if (other.imageUrlMedium != null)
				return false;
		} else if (!imageUrlMedium.equals(other.imageUrlMedium))
			return false;
		if (imageUrlSmall == null) {
			if (other.imageUrlSmall != null)
				return false;
		} else if (!imageUrlSmall.equals(other.imageUrlSmall))
			return false;
		if (insurerName == null) {
			if (other.insurerName != null)
				return false;
		} else if (!insurerName.equals(other.insurerName))
			return false;
		if (licensePlateNumber == null) {
			if (other.licensePlateNumber != null)
				return false;
		} else if (!licensePlateNumber.equals(other.licensePlateNumber))
			return false;
		if (locationCity == null) {
			if (other.locationCity != null)
				return false;
		} else if (!locationCity.equals(other.locationCity))
			return false;
		if (locationCountry == null) {
			if (other.locationCountry != null)
				return false;
		} else if (!locationCountry.equals(other.locationCountry))
			return false;
		if (manufacturerName == null) {
			if (other.manufacturerName != null)
				return false;
		} else if (!manufacturerName.equals(other.manufacturerName))
			return false;
		if (mileage != other.mileage)
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (price != other.price)
			return false;
		if (transmissionTypeName == null) {
			if (other.transmissionTypeName != null)
				return false;
		} else if (!transmissionTypeName.equals(other.transmissionTypeName))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userImageUrlMedium == null) {
			if (other.userImageUrlMedium != null)
				return false;
		} else if (!userImageUrlMedium.equals(other.userImageUrlMedium))
			return false;
		if (userImageUrlSmall == null) {
			if (other.userImageUrlSmall != null)
				return false;
		} else if (!userImageUrlSmall.equals(other.userImageUrlSmall))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarDto [id=" + id + ", description=" + description + ", year=" + year + ", transmissionTypeName="
				+ transmissionTypeName + ", fuelTypeName=" + fuelTypeName + ", carTypeName=" + carTypeName
				+ ", licensePlateNumber=" + licensePlateNumber + ", manufacturerName=" + manufacturerName
				+ ", insurerName=" + insurerName + ", modelName=" + modelName + ", numberOfSeats=" + numberOfSeats
				+ ", features=" + features + ", mileage=" + mileage + ", price=" + price + ", locationCountry="
				+ locationCountry + ", locationCity=" + locationCity + ", imageUrlSmall=" + imageUrlSmall
				+ ", imageUrlMedium=" + imageUrlMedium + ", imageUrlLarge=" + imageUrlLarge + ", userId=" + userId
				+ ", userFirstName=" + userFirstName + ", userImageUrlSmall=" + userImageUrlSmall
				+ ", userImageUrlMedium=" + userImageUrlMedium + "]";
	}

	
}
