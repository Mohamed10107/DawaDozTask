package com.dawadoz.dawadoztask.utils;

import java.util.Calendar;

public class DateFormatter
{
	private static String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private static String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public static String format(String date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(date) * 1000);
		calendar.get(Calendar.DAY_OF_WEEK);
		return days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + ",  " + calendar.get(Calendar.DAY_OF_MONTH) + " " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
	}
}
