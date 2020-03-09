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
	
	public static String randomTenDigitsUUID()
	{
		  // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder("10"); 
  
        for (int i = 0; i <10; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
	}

	public static String generateOTP()
	{
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(899999) + 100000;
		return String.format("%05d", num);
	}
}
