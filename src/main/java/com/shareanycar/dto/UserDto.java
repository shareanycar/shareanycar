package com.shareanycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String password;
	private String phone;
	private String passport;
	private String drivingLicense;
	private String description;
	private String userImageUrlSmall;
	private String userImageUrlMedium;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public String getPassport() {
		return passport;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public String getDescription() {
		return description;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((drivingLicense == null) ? 0 : drivingLicense.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((userImageUrlMedium == null) ? 0 : userImageUrlMedium.hashCode());
		result = prime * result + ((userImageUrlSmall == null) ? 0 : userImageUrlSmall.hashCode());
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
		UserDto other = (UserDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (drivingLicense == null) {
			if (other.drivingLicense != null)
				return false;
		} else if (!drivingLicense.equals(other.drivingLicense))
			return false;
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
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
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
		return true;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", passport=" + passport
				+ ", drivingLicense=" + drivingLicense + ", description=" + description + ", userImageUrlSmall="
				+ userImageUrlSmall + ", userImageUrlMedium=" + userImageUrlMedium + "]";
	}
			
}
