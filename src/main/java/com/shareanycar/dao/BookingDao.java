package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.model.Booking;

public class BookingDao extends BasicDao<Booking>{

	@Inject
	public ExtDao<?> extDao;
	
	public BookingDao() {
		super("Booking");
	}
	
	public List<Booking> clientBookings(Long userId){
		return (List<Booking>) extDao.findListByParam("from Booking where user_id = :userId", "userId", userId);
	}

}
