package com.shareanycar.mapping;

import org.modelmapper.PropertyMap;

import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;

public class Booking2BookingDtoMap  extends PropertyMap<Booking,BookingDto>{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		skip().setDateFrom(null);
		skip().setDateTo(null);
	}

}
