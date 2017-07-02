package com.shareanycar.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
