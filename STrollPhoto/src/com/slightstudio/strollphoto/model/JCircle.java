package com.slightstudio.strollphoto.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.slightstudio.common.JSONUtils;

public class JCircle extends JShape {
	private JPoint center;
	private double radius;
	
	public JPoint getCenter() {
		return center;
	}
	public void setCenter(JPoint center) {
		this.center = center;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		super.parse(json);
		
		setCenter(JPoint.fromString(JSONUtils.getString(json, "center")));
		setRadius(JSONUtils.getDouble(json, "radius"));
	}
	
	@Override
	public SFrame getFrame() {
		double x = center.getX() - radius;
		double y = center.getY() - radius;
		
		return new SFrame(new SSPoint(x, y), new SSize(radius * 2, radius * 2));
	}
}
