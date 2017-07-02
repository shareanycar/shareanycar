package com.shareanycar.dto;

public class CarAvailabilityDto {
	private Long id;
	private String date;
	private String availability;

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getAvailability() {
		return availability;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availability == null) ? 0 : availability.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CarAvailabilityDto other = (CarAvailabilityDto) obj;
		if (availability == null) {
			if (other.availability != null)
				return false;
		} else if (!availability.equals(other.availability))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarAvailabilityDto [id=" + id + ", date=" + date + ", availability=" + availability + "]";
	}

}
