package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
	
		private Long id;
		private String name;
		private String description;
		private int year;
		private String country;
		private String city;
		private String transmissionType;
		private String carType;
		private int numberOfSeats;
		
		private String mainImageUrl;

		public CarDto(){}
		
		public CarDto(Long id, String name, String description, int year, String country, String city,
				String transmissionType, String carType, int numberOfSeats) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.year = year;
			this.country = country;
			this.city = city;
			this.transmissionType = transmissionType;
			this.carType = carType;
			this.numberOfSeats = numberOfSeats;
		}

		public CarDto(Long id, String name, String description, int year, String country, String city,
				String transmissionType, String carType, int numberOfSeats, String mainImageUrl) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.year = year;
			this.country = country;
			this.city = city;
			this.transmissionType = transmissionType;
			this.carType = carType;
			this.numberOfSeats = numberOfSeats;
			this.mainImageUrl = mainImageUrl;
		}

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

		public String getCountry() {
			return country;
		}

		public String getCity() {
			return city;
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

		public String getMainImageUrl() {
			return mainImageUrl;
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

		public void setCountry(String country) {
			this.country = country;
		}

		public void setCity(String city) {
			this.city = city;
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

		public void setMainImageUrl(String mainImageUrl) {
			this.mainImageUrl = mainImageUrl;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((carType == null) ? 0 : carType.hashCode());
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result + ((country == null) ? 0 : country.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((mainImageUrl == null) ? 0 : mainImageUrl.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + numberOfSeats;
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
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (country == null) {
				if (other.country != null)
					return false;
			} else if (!country.equals(other.country))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (mainImageUrl == null) {
				if (other.mainImageUrl != null)
					return false;
			} else if (!mainImageUrl.equals(other.mainImageUrl))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (numberOfSeats != other.numberOfSeats)
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
					+ ", country=" + country + ", city=" + city + ", transmissionType=" + transmissionType
					+ ", carType=" + carType + ", numberOfSeats=" + numberOfSeats + ", mainImageUrl=" + mainImageUrl
					+ "]";
		}

				
						
}
