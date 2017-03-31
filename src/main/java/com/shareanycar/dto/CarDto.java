package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
	
		private Long id;
		private String name;
		private String description;
		private int year;
		private String transmissionType;
		private String fuelType;
		private int numberOfSeats;
		private String carType;
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

		public String getTransmissionType() {
			return transmissionType;
		}

		public String getCarType() {
			return carType;
		}

		public int getNumberOfSeats() {
			return numberOfSeats;
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

		

		public void setTransmissionType(String transmissionType) {
			this.transmissionType = transmissionType;
		}

		public void setCarType(String carType) {
			this.carType = carType;
		}

		public void setNumberOfSeats(int numberOfSeats) {
			this.numberOfSeats = numberOfSeats;
		}

		public void setDefaultImageUrl(String defaultImageUrl) {
			this.defaultImageUrl = defaultImageUrl;
		}

		public String getFuelType() {
			return fuelType;
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

		public void setFuelType(String fuelType) {
			this.fuelType = fuelType;
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

		public String getLocationCountry() {
			return locationCountry;
		}


		public String getLocationCity() {
			return locationCity;
		}

		public void setLocationCountry(String locationCountry) {
			this.locationCountry = locationCountry;
		}



		public void setLocationCity(String locationCity) {
			this.locationCity = locationCity;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((carType == null) ? 0 : carType.hashCode());
			result = prime * result + ((defaultImageUrl == null) ? 0 : defaultImageUrl.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((features == null) ? 0 : features.hashCode());
			result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
			result = prime * result + ((locationCountry == null) ? 0 : locationCountry.hashCode());
			result = prime * result + mileage;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + numberOfSeats;
			result = prime * result + price;
			result = prime * result + ((transmissionType == null) ? 0 : transmissionType.hashCode());
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
			if (carType == null) {
				if (other.carType != null)
					return false;
			} else if (!carType.equals(other.carType))
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
			if (fuelType == null) {
				if (other.fuelType != null)
					return false;
			} else if (!fuelType.equals(other.fuelType))
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
			if (transmissionType == null) {
				if (other.transmissionType != null)
					return false;
			} else if (!transmissionType.equals(other.transmissionType))
				return false;
			if (year != other.year)
				return false;
			return true;
		}



		@Override
		public String toString() {
			return "CarDto [id=" + id + ", name=" + name + ", description=" + description + ", year=" + year
					+ ", transmissionType=" + transmissionType + ", fuelType=" + fuelType + ", numberOfSeats="
					+ numberOfSeats + ", carType=" + carType + ", features=" + features + ", mileage=" + mileage
					+ ", price=" + price + ", locationCountry=" + locationCountry + ", locationCity=" + locationCity
					+ ", defaultImageUrl=" + defaultImageUrl + "]";
		}

		
								
}
