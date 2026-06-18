package com.global.system.TimeUtilsMethods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateToString {
	
	
	public static String convertToSring(LocalDateTime ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm:ss a", Locale.US);
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
		
	}

}
