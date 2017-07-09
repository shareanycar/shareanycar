package com.shareanycar.mapping;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;

public class Booking2BookingDtoConv extends AbstractConverter<Booking,BookingDto>{

	@Override
	protected BookingDto convert(Booking source) {
		BookingDto bookingDto = new BookingDto();
		
		bookingDto.setDateFrom(source.getDateFrom().toString());
		bookingDto.setDateTo(source.getDateTo().toString());
		bookingDto.setCarId(source.getCar().getId());
		bookingDto.setClientId(source.getUser().getId());
		bookingDto.setClientName(source.getUser().getFirstName() + " " + source.getUser().getLastName());
		bookingDto.setOwnerId(source.getCar().getUser().getId());
		bookingDto.setOwnerName(source.getCar().getUser().getFirstName() + " " + source.getCar().getUser().getLastName());
		bookingDto.setManufacturer(source.getCar().getManufacturer().getName());
		bookingDto.setModel(source.getCar().getModelName());
		bookingDto.setTransmissionType(source.getCar().getTransmissionType().getName());
		bookingDto.setStatus(source.getStatus());
		bookingDto.setCarMakeYear(source.getCar().getYear());
		bookingDto.setTotalPrice(source.getTotalPrice());
		bookingDto.setNumberOfDays(source.getNumberOfDays());
		
		return bookingDto;
	}

}
