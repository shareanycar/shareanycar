package com.shareanycar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shareanycar.enums.AvailabilityStatus;

@Entity
public class CarAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	private AvailabilityStatus availability;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;

	public CarAvailability() {
	}

	public CarAvailability(LocalDate date, AvailabilityStatus availability, Car car) {
		this.date = date;
		this.availability = availability;
		this.car = car;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Car getCar() {
		return car;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public AvailabilityStatus getAvailability() {
		return availability;
	}

	public void setAvailability(AvailabilityStatus availability) {
		this.availability = availability;
	}

}
