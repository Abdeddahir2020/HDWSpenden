package de.hdw.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HDWUtils {

	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}

	public static Date convertStringToTimestamp(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			// convert YY to YYYY
			if (strDate.length() < 10) {
				StringBuffer bf = new StringBuffer(strDate);
				bf.insert(6, "20");
				strDate = bf.toString();
			}

			Date date = formatter.parse(strDate);

			return date;

		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
	
	public static String getMonat(Date date) {
		SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM", Locale.GERMAN);
		return simpleformat.format(date);
	}
	
	public static String getJahr(Date date) {
		SimpleDateFormat simpleformat = new SimpleDateFormat("YYYY");
		return simpleformat.format(date);
	}
}
