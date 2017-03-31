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
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String country;
	@NotEmpty
	private String city;

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "location")
	private Set<Car> cars;
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true,  mappedBy = "location")
	private Set<Owner> owners;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Owner> getOwners() {
		return owners;
	}

	public void setOwners(Set<Owner> owners) {
		this.owners = owners;
	}

	public static class Builder{
		private String country;
		private String city;
		
		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}
		
		public Location build(){
			Location location = new Location();
			location.country = this.country;
			location.city = this.city;
			return location;
		}
	}
	

		

}
