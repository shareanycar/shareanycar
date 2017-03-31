package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class OwnerDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String locationCountry;
	private String locationCity;
	private String phone;
	private String email;
	private String password;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
		result = prime * result + ((locationCountry == null) ? 0 : locationCountry.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		OwnerDto other = (OwnerDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "OwnerDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", locationCountry="
				+ locationCountry + ", locationCity=" + locationCity + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + "]";
	}

	

	

		
}
