package com.shareanycar.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.CarDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.User;

@Service
public class MiscUtils {

	@Inject
	public CarDao carDao;
	
	public String randonString() {
		Random random = new SecureRandom();
		String hash = new BigInteger(130, random).toString(32);
		return hash;
	}

	public LocalDate String2LocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		return LocalDate.parse(date, formatter);
	}

	public List<LocalDate> listOfDates(LocalDate fromDate, LocalDate toDate) {
		if (fromDate.compareTo(toDate) > 0)
			throw new IllegalArgumentException("date from: " + fromDate.toString() + "; date to: " + toDate.toString());

		List<LocalDate> dates = new ArrayList<>();

		LocalDate tmpDate = fromDate;
		while (tmpDate.compareTo(toDate) <= 0) {
			dates.add(tmpDate);
			tmpDate = tmpDate.plusDays(1);
		}
		return dates;
	}
		
}
