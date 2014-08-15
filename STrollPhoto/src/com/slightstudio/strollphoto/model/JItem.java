package com.slightstudio.strollphoto.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JItem {
	
	private int index;
	private SFrame frame;
	private double skewAngle;
	
	public abstract void parse(JSONObject json) throws JSONException;
	
	
	public SFrame getFrame() {
		return frame;
	}

	public void setFrame(SFrame frame) {
		this.frame = frame;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	

	public double getSkewAngle() {
		return skewAngle;
	}

	public void setSkewAngle(double skewAngle) {
		this.skewAngle = skewAngle;
	}
}
