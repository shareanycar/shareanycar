package com.shareanycar.dto;

import com.shareanycar.enums.BookingStatus;

public class BookingDto {
	
	private Long id;
	private Long carId;
	private Long userId;
	private String ownerName;
	private String clientName;
	private String manufacturer;
	private String model;
	private String transmissionType;
	private int year;
	private String dateFrom;
	private String dateTo;
	
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	public String getDateFrom() {
		return dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}

	private BookingStatus status;
	
	public Long getId() {
		return id;
	}
	public Long getCarId() {
		return carId;
	}
	public Long getUserId() {
		return userId;
	}
	
	public BookingStatus getStatus() {
		return status;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public String getOwnerName() {
		return ownerName;
	}
	public String getClientName() {
		return clientName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transmissionType == null) ? 0 : transmissionType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		BookingDto other = (BookingDto) obj;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (status != other.status)
			return false;
		if (transmissionType == null) {
			if (other.transmissionType != null)
				return false;
		} else if (!transmissionType.equals(other.transmissionType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BookingDto [id=" + id + ", carId=" + carId + ", userId=" + userId + ", ownerName=" + ownerName
				+ ", clientName=" + clientName + ", manufacturer=" + manufacturer + ", model=" + model
				+ ", transmissionType=" + transmissionType + ", year=" + year + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + ", status=" + status + "]";
	}
	
	
}
