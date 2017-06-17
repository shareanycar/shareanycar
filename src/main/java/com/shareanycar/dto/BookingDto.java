package com.shareanycar.dto;

import com.shareanycar.enums.BookingStatus;

public class BookingDto {
	
	private Long id;
	private Long carId;
	private Long ownerId;
	private String ownerName;
	private Long clientId;
	private String clientName;
	private String manufacturer;
	private String model;
	private String transmissionType;
	private int carMakeYear;
	private String dateFrom;
	private String dateTo;
	private String note;
	private BookingStatus status;
	private Double totalPrice; 
	private Integer numberOfDays;
	
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

	public Long getId() {
		return id;
	}
	
	public Long getCarId() {
		return carId;
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
	
	public String getTransmissionType() {
		return transmissionType;
	}
	
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	
	public int getCarMakeYear() {
		return carMakeYear;
	}
	
	public void setCarMakeYear(int carMakeYear) {
		this.carMakeYear = carMakeYear;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}
	
	public Long getClientId() {
		return clientId;
	}
	
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + carMakeYear;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((numberOfDays == null) ? 0 : numberOfDays.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((transmissionType == null) ? 0 : transmissionType.hashCode());
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
		if (carMakeYear != other.carMakeYear)
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
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
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (numberOfDays == null) {
			if (other.numberOfDays != null)
				return false;
		} else if (!numberOfDays.equals(other.numberOfDays))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (status != other.status)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (transmissionType == null) {
			if (other.transmissionType != null)
				return false;
		} else if (!transmissionType.equals(other.transmissionType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingDto [id=" + id + ", carId=" + carId + ", ownerId=" + ownerId + ", ownerName=" + ownerName
				+ ", clientId=" + clientId + ", clientName=" + clientName + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", transmissionType=" + transmissionType + ", carMakeYear=" + carMakeYear
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", note=" + note + ", status=" + status
				+ ", totalPrice=" + totalPrice + ", numberOfDays=" + numberOfDays + "]";
	}

	
		
}
