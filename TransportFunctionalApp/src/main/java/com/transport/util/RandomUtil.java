package com.transport.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class RandomUtil {
	
	public String generateSecureRandom(int size){
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[size];
		random.nextBytes(bytes);
		return Base64Utils.encodeToUrlSafeString(bytes);
	}
}
