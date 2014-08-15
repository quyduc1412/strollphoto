package com.slightstudio.strollphoto.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.R;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.JTemplate;

public class TemplateView extends FrameLayout{
	private String photos;
	private String background;
	private Context context;
	private JTemplate jTemplate;
	public TemplateView(Context context,JTemplate jTemplate) {
		super(context);
		this.context = context;
		this.jTemplate = jTemplate; 
		setup();
	}
	public void setup(){
		ImageView imageView = new ImageView(context);
		imageView.setBackgroundResource(R.drawable.sample1);
    	JFrame frame = new JFrame(0, 0, jTemplate.getSize().getWidth(), jTemplate.getSize().getHeight());
		UIUtils.addView(this, imageView, frame, 1);
	}
}
