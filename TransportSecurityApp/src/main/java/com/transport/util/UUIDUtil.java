package com.transport.util;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDUtil {
	
	public static String randomUUID(){
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
	public static String generateToken() {
		return UUID.randomUUID().toString();
	}
	public static String generateOTP()
	{
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(899999) + 100000;
		return String.format("%05d", num);
	}
}
