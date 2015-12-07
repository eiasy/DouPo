package com.hygame.dpcq.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarTools {
	//获得系统时间 以yyyy-MM-dd 形式输出
	public static String getDate(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String dateString = formatter.format(currentTime); 
		return dateString;
	}
}
