package com.slightstudio.strollphoto.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.slightstudio.common.JSONUtils;

public class JShape extends JItem {
	
	private String effect;
	private int borderWidth;
	private String borderColor;
	
	public int getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	@Override
	public void parse(JSONObject json) throws JSONException {
		setEffect(JSONUtils.getString(json, "effect"));
		setBorderWidth(JSONUtils.getInt(json, "borderWidth"));
		setBorderColor(JSONUtils.getString(json, "borderColor"));
	}
}
