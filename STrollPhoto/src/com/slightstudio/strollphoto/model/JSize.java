package com.slightstudio.strollphoto.model;

import com.slightstudio.common.StringUtils;

public class JSize {
	private double width;
	private double height;
	
	public double getWidth() {
		return width;
	}
	public JSize setWidth(double width) {
		this.width = width;
		return this;
	}
	public double getHeight() {
		return height;
	}
	public JSize setHeight(double height) {
		this.height = height;
		return this;
	}
	
	public static JSize fromString(String value) {
		if (StringUtils.isBlank(value)) 
			return null;
		
		String[] wh = value.split(",");
		
		JSize size = new JSize();
		size.setWidth(Double.valueOf(wh[0]));
		size.setHeight(Double.valueOf(wh[1]));
		
		return size;
	}
}