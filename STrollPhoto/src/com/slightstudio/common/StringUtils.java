package com.slightstudio.common;



public class StringUtils {
	public static String toUpperCase(String s) {
		if (s == null) return "";
		return s.toUpperCase();
	}
	
	public static boolean isBlank(String s) {
		return s == null || s.length() <= 0;
	}
	
	public static String getString(int id) {
		//return App.getContext().getResources().getString(id);
		return "";
	}
}
