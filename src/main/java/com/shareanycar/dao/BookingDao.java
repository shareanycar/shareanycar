package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Booking;

@Service
public class BookingDao extends BasicDao<Booking>{

	@Inject
	public ExtDao<?> extDao;
	
	public BookingDao() {
		super("Booking");
	}
	
	public Booking findBookingByCustomerIdAndCarId(Long customerId, Long carId){
		try {
			return (Booking) extDao.findOneByParams("from Booking where customer_id = :customerId and car_id = :carId",
					new String[] { "customer_id", "car_id" }, new Object[] { customerId, carId });
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Booking> findBookingByCarId(Long id){
		return (List<Booking>) extDao.findListByParam("from Booking where  car_id = :carId", "car_id", id);
	}
		
}
