package com.slightstudio.common;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
	public static String getString(JSONObject json, String key) {
		String value = null;
		if (json.has(key)) {
			 try {
				value = json.getString(key);
			} catch (JSONException e) {
				return null;
			}
		}
			
		return value;
	}
	
	public static double getDouble(JSONObject json, String key) {
		double value = 0;
		if (json.has(key)) {
			 try {
				value = json.getDouble(key);
			} catch (JSONException e) {
				return 0;
			}
		}
			
		return value;
	}
	
	public static int getInt(JSONObject json, String key) {
		int value = 0;
		if (json.has(key)) {
			 try {
				value = json.getInt(key);
			} catch (JSONException e) {
				return 0;
			}
		}
			
		return value;
	}
}
