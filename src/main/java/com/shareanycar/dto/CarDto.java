package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
	
		private Long id;
		private String name;
		private String description;
		private int year;
		private String transmissionTypeName;
		private String fuelTypeName;
		private String carTypeName;
		private int numberOfSeats;
		private String features;
		private int mileage;
		private int price;
		private String locationCountry;
		private String locationCity;
		private String defaultImageUrl;
		public Long getId() {
			return id;
		}
		public String getName() {
			return name;
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
		public String getDefaultImageUrl() {
			return defaultImageUrl;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public void setName(String name) {
			this.name = name;
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
		public void setDefaultImageUrl(String defaultImageUrl) {
			this.defaultImageUrl = defaultImageUrl;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((carTypeName == null) ? 0 : carTypeName.hashCode());
			result = prime * result + ((defaultImageUrl == null) ? 0 : defaultImageUrl.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((features == null) ? 0 : features.hashCode());
			result = prime * result + ((fuelTypeName == null) ? 0 : fuelTypeName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
			result = prime * result + ((locationCountry == null) ? 0 : locationCountry.hashCode());
			result = prime * result + mileage;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + numberOfSeats;
			result = prime * result + price;
			result = prime * result + ((transmissionTypeName == null) ? 0 : transmissionTypeName.hashCode());
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
			if (defaultImageUrl == null) {
				if (other.defaultImageUrl != null)
					return false;
			} else if (!defaultImageUrl.equals(other.defaultImageUrl))
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
			if (mileage != other.mileage)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
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
			if (year != other.year)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "CarDto [id=" + id + ", name=" + name + ", description=" + description + ", year=" + year
					+ ", transmissionTypeName=" + transmissionTypeName + ", fuelTypeName=" + fuelTypeName
					+ ", carTypeName=" + carTypeName + ", numberOfSeats=" + numberOfSeats + ", features=" + features
					+ ", mileage=" + mileage + ", price=" + price + ", locationCountry=" + locationCountry
					+ ", locationCity=" + locationCity + ", defaultImageUrl=" + defaultImageUrl + "]";
		}
		
																	
}
