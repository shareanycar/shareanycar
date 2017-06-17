package com.shareanycar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.shareanycar.enums.BookingStatus;

@Entity
public class Booking implements Comparable<Booking>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDate dateFrom;
	
	@NotNull
	private LocalDate dateTo;
	
	@NotNull
	private BookingStatus status;
	
	private Double totalPrice;
	
	private Integer numberOfDays;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private String note;

	public Long getId() {
		return id;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public Car getCar() {
		return car;
	}

	public User getUser() {
		return user;
	}

	public String getNote() {
		return note;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setNote(String note) {
		this.note = note;
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

	@Override
	public String toString() {
		return "Booking [id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", status=" + status
				+ ", totalPrice=" + totalPrice + ", numberOfDays=" + numberOfDays + ", car=" + car + ", user=" + user
				+ ", note=" + note + "]";
	}

	@Override
	public int compareTo(Booking o) {
		return this.dateFrom.compareTo(o.dateFrom);
	}
}
