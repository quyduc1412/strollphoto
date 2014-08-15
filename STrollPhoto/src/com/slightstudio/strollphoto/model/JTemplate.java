package com.slightstudio.strollphoto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.slightstudio.common.JSONUtils;

public class JTemplate {
	private String name;
	private JSize size;
	private String thumbnail;
	private int numberOfImages;
	private double ratio;
	private boolean isDownloaded;
	private JSONObject json; 
	private String type;
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

//	private List<JItem> items;

	public String getName() {
		return name;
	}


	public boolean isDownloaded() {
		return isDownloaded;
	}


	public void setDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}


	public void setName(String name) {
		this.name = name;
	}

	public JSize getSize() {
		return size;
	}

	public void setSize(JSize size) {
		this.size = size;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getNumberOfImages() {
		return numberOfImages;
	}

	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages = numberOfImages;
	}

//	public List<JItem> getItems() {
//		return items;
//	}
//
//	public void setItems(List<JItem> items) {
//		this.items = items;
//	} 
	
	public boolean isClassic() {
		return name != null && name.startsWith("c");
	}
	
	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public JSONObject getJSON() {
		return json;
	}

	public void setJSON(JSONObject json) {
		this.json = json;
	}
	
}
