package com.shareanycar.mapping;

import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;

public class Booking2BookingDtoConv extends AbstractConverter<Booking,BookingDto>{

	@Override
	protected BookingDto convert(Booking source) {
		BookingDto bookingDto = new BookingDto();
		
		bookingDto.setDateFrom(source.getDateFrom().format(DateTimeFormatter.ISO_DATE));
		bookingDto.setDateTo(source.getDateTo().format(DateTimeFormatter.ISO_DATE));
		bookingDto.setCarId(source.getCar().getId());
		bookingDto.setUserId(source.getUser().getId());
		bookingDto.setClientName(source.getUser().getFirstName());
		bookingDto.setOwnerName(source.getCar().getUser().getFirstName());
		bookingDto.setManufacturer(source.getCar().getManufacturer().getName());
		bookingDto.setModel(source.getCar().getModelName());
		bookingDto.setTransmissionType(source.getCar().getTransmissionType().getName());
		bookingDto.setStatus(source.getStatus());
		
		return bookingDto;
	}

}
