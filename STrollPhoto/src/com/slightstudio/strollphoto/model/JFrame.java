package com.slightstudio.strollphoto.model;

import com.slightstudio.common.StringUtils;

public class JFrame {
	private double x;
	private double y;
	private double width;
	private double height;

	public JFrame(double x, double y, double width, double height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	public JFrame(String value) {
		if (!StringUtils.isBlank(value)) {
			String[] xywh = value.split(",");
			if (xywh.length == 4) {
				setX(Double.valueOf(xywh[0]));
				setY(Double.valueOf(xywh[1]));
				setWidth(Double.valueOf(xywh[2]));
				setHeight(Double.valueOf(xywh[3]));
			}
		}
	}

	public String getString() {
		return "{" + x + ", " + y + ", " + width + ", " + height + "}";
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
}
