package com.slightstudio.strollphoto.model;

public class STemplate {
	
	private SSize size;
	private double ratio;

	public STemplate() {
		ratio = 1;
	}
	
	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public void setSize(SSize size) {
		this.size = size;
	}

	public SSize getSize() {
		return size;
	}
}
