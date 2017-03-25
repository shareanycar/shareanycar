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
public class Customer {
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<Comment> comments;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<Booking> bookings;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "customer")
	private Set<Conversation> conversations;

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
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
