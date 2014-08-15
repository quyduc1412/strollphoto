package com.slightstudio.strollphoto.model;

public class SFrame {
	private SSPoint point;
	private SSize size;

	public SFrame() {
	}

	public SFrame(SSPoint point, SSize size) {
		super();
		this.point = point;
		this.size = size;
	}



	public SSPoint getPoint() {
		return point;
	}

	public void setPoint(SSPoint point) {
		this.point = point;
	}

	public SSize getSize() {
		return size;
	}

	public void setSize(SSize size) {
		this.size = size;
	}

}
