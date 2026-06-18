package com.global.system.TimeUtilsMethods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StrignDateToLocalDateTime {
	
	public static LocalDateTime convertToDate(String localdate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm:ss a", Locale.US);
		LocalDateTime localDateTime = LocalDateTime.parse(localdate,formatter);
		return localDateTime;
	}

}
