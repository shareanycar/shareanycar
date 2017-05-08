package com.shareanycar.dto;

import java.time.LocalDateTime;

import com.shareanycar.enums.BookingStatus;

public class BookingDto {
	
	private Long id;
	private Long carId;
	private Long userId;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private BookingStatus status;
	
	
	
}
