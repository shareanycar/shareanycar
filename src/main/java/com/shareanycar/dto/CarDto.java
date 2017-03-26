package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
	
		private Long id;
		private String name;
		private String brand;
		private String model;
		private String description;
		private int year;
		private String country;
		private String city;
		private String mainImageUrl;
		
		public CarDto(){}
		
		public CarDto(Long id,String brand, String model, String name, String description, int year, String country, String city) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.year = year;
			this.country = country;
			this.city = city;
			this.brand = brand;
			this.model = model;
		}
		

		public CarDto(Long id, String name, String brand, String model, String description, int year, String country,
				String city, String mainImageUrl) {
			this.id = id;
			this.name = name;
			this.brand = brand;
			this.model = model;
			this.description = description;
			this.year = year;
			this.country = country;
			this.city = city;
			this.mainImageUrl = mainImageUrl;
		}

		public String getMainImageUrl() {
			return mainImageUrl;
		}

		public void setMainImageUrl(String mainImageUrl) {
			this.mainImageUrl = mainImageUrl;
		}

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
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((brand == null) ? 0 : brand.hashCode());
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result + ((country == null) ? 0 : country.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((mainImageUrl == null) ? 0 : mainImageUrl.hashCode());
			result = prime * result + ((model == null) ? 0 : model.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			if (brand == null) {
				if (other.brand != null)
					return false;
			} else if (!brand.equals(other.brand))
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
			if (model == null) {
				if (other.model != null)
					return false;
			} else if (!model.equals(other.model))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (year != other.year)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CarDto [id=" + id + ", name=" + name + ", brand=" + brand + ", model=" + model + ", description="
					+ description + ", year=" + year + ", country=" + country + ", city=" + city + ", mainImageUrl="
					+ mainImageUrl + "]";
		}
		
				
}
