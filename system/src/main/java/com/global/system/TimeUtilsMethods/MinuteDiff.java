package com.global.system.TimeUtilsMethods;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MinuteDiff {
	
	
	public static long minDiff(LocalDateTime pldt,LocalDateTime Nldt) {
		
		 LocalTime time11 = LocalTime.of(pldt.getHour(), pldt.getMinute(), pldt.getSecond()); 
	        LocalTime time22 = LocalTime.of(Nldt.getHour(), Nldt.getMinute(), Nldt.getSecond()); 
	        
	        long minutes 
	        = ChronoUnit.MINUTES.between(time11, time22) % 60; 
		
		return minutes;
	}

}