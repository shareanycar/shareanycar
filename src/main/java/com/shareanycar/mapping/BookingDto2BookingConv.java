package com.shareanycar.mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.BookingDto;
import com.shareanycar.model.Booking;

public class BookingDto2BookingConv extends AbstractConverter<BookingDto, Booking>{

	@Override
	protected Booking convert(BookingDto source) {
		Booking booking = new Booking();
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		booking.setDateFrom(LocalDateTime.parse(source.getDateFrom(),formatter));
		booking.setDateTo(LocalDateTime.parse(source.getDateTo(),formatter));
		
		return booking;
	}

}
