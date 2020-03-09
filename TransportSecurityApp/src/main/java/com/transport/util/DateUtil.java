package com.transport.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

private DateUtil() {};

public static Date currentDate(){
return new Date();
}

public static Date currentDateTruncated(){
Calendar calendar = new GregorianCalendar();
calendar.set(Calendar.MINUTE, 0);
calendar.set(Calendar.SECOND, 0);
calendar.set(Calendar.HOUR_OF_DAY, 0);
return calendar.getTime();
}

public static Date getDateAfterDays(int days){
Calendar calendar = new GregorianCalendar();
calendar.add(Calendar.DAY_OF_MONTH, days);
return calendar.getTime();
}

public static Date getTruncatedDateAfterDays(int days){
Calendar calendar = new GregorianCalendar();
calendar.add(Calendar.DAY_OF_MONTH, days);
calendar.set(Calendar.MINUTE, 0);
calendar.set(Calendar.SECOND, 0);
calendar.set(Calendar.HOUR_OF_DAY, 0);
return calendar.getTime();
}

public static Date getDateAfterMins(int minutes){
Calendar calendar = new GregorianCalendar();
calendar.add(Calendar.MINUTE, minutes);
return calendar.getTime();
}
}