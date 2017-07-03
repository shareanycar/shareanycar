package com.shareanycar.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jvnet.hk2.annotations.Service;

@Service
public class MiscUtils {
	
	public String randonString(){
		Random random = new SecureRandom();
		String hash = new BigInteger(130, random).toString(32);
		return hash;
	}

	public LocalDate String2LocalDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		return LocalDate.parse(date,formatter).plusDays(1);
	}
	
	public List<LocalDate> listOfDates(LocalDate dateFrom, LocalDate dateTo) {
		if(dateFrom.compareTo( dateTo) >= 0)
			throw new IllegalArgumentException("date from: " +dateFrom.toString() + "; date to: " + dateTo.toString());
		
		List<LocalDate> dates = new ArrayList<>();

		LocalDate tmpDate = dateFrom.plusDays(1);
		while (tmpDate.compareTo(dateTo) <= 1) {
			dates.add(tmpDate);
			tmpDate = tmpDate.plusDays(1);
		}
		return dates;
	} 
}
