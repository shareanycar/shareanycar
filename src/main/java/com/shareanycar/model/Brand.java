package com.shareanycar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Brand {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "brand")
	private List<Car> cars;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "brand")
	private List<Model> models;

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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((models == null) ? 0 : models.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Brand other = (Brand) obj;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (models == null) {
			if (other.models != null)
				return false;
		} else if (!models.equals(other.models))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", cars=" + cars + ", models=" + models + "]";
	}

	
	
}
