package com.slightstudio.strollphoto.model;

public class SSize {
	private double width;
	private double height;

	public SSize() {
	}

	public SSize(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public SSize setWidth(double width) {
		this.width = width;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public SSize setHeight(double height) {
		this.height = height;
		return this;
	}

}
