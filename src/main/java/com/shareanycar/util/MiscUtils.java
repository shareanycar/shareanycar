package com.shareanycar.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.jvnet.hk2.annotations.Service;

@Service
public class MiscUtils {
	
	public String randonString(){
		Random random = new SecureRandom();
		String hash = new BigInteger(130, random).toString(32);
		return hash;
	}

	
}
