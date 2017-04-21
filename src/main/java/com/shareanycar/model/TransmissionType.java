package com.shareanycar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TransmissionType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	public TransmissionType() {
	}

	public TransmissionType(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "transmissionType")
	private Set<Car> cars;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	
}
