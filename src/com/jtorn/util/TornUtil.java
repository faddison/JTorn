package com.jtorn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jtorn.bot.core.TornConstants;

public class TornUtil
{

	public static String getTime(String stringFormat)
	{
		return getTime(stringFormat, 0);
	}
	
	public static String getTime(String stringFormat, int minutes)
	{
		Calendar futureTime = Calendar.getInstance();
		futureTime.add(Calendar.MINUTE, minutes);
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringFormat);
		return dateFormat.format(futureTime.getTime());
	}
}
