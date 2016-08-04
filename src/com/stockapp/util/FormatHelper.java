package com.stockapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper {	
	/*
	 * Date should be formatted as Jan-01-2009
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
		return sdf.format(date);
	}
	
	/*
	 * This method should replace the numerous roundToDecimal methods found in this class - Mahesh
	 */
	public static String roundToDec(double number, double digits) {
		double power = Math.pow(10, digits);
		double number2 = number * power;
		long number3 = Math.round(number2);
		double number4 = number3 / (power * 1.0);
		StringBuffer sb = new StringBuffer();
		sb.append(number4);
		
		while (power > 1) {
			if ((number3 % power) == 0)
				sb.append("0");
			power = power / 10;
			
		}
		return sb.toString();
	}
	
}
