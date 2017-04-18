package com.shareanycar.dto;

public class UserInfoDto {
	private Long id;
	private String firstName;
	private String userImageUrlSmall;
	private String userImageUrlMedium;
	
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getUserImageUrlSmall() {
		return userImageUrlSmall;
	}
	public String getUserImageUrlMedium() {
		return userImageUrlMedium;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserInfoDto other = (UserInfoDto) obj;
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
		return "UserInfoDto [id=" + id + ", firstName=" + firstName + ", userImageUrlSmall=" + userImageUrlSmall
				+ ", userImageUrlMedium=" + userImageUrlMedium + "]";
	}
	
	
	
}
