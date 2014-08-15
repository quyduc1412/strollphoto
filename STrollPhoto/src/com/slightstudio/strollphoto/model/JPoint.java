package com.slightstudio.strollphoto.model;

import com.slightstudio.common.StringUtils;

public class JPoint {
	private double x;
	private double y;

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public static JPoint fromString(String value) {
		if (StringUtils.isBlank(value)) 
			return null;
		
		String[] xy = value.split(",");
		
		JPoint point = new JPoint();
		
		point.setX(Double.valueOf(xy[0]));
		point.setY(Double.valueOf(xy[1]));
		
		return point;
	}
}