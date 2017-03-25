package com.shareanycar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String phone;
	
	private String token;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "owner")
	private Set<Car> cars;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "owner")
	private Set<Conversation> conversations;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

	
}
