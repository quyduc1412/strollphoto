package com.slightstudio.strollphoto.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.polites.android.GestureImageView;
import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.R;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.JRectangle;
import com.slightstudio.strollphoto.model.JTemplate;
import com.slightstudio.strollphoto.model.SFrame;
import com.slightstudio.strollphoto.model.SSPoint;
import com.slightstudio.strollphoto.model.SSize;
import com.slightstudio.strollphoto.model.STemplate;

public class TemplateView extends FrameLayout {
	private String photos;
	private String background;
	private Context context;
	private STemplate jTemplate;

	public TemplateView(Context context, STemplate jTemplate) {
		super(context);
		this.context = context;
		this.jTemplate = jTemplate;
		photos = "drawable://" + R.drawable.a1111;
		setup();
	}

	public void setup() {
		GestureImageView mImageView = new GestureImageView(context);
		mImageView.setScaleType(ScaleType.CENTER_CROP);
		ImageSize targetSize = new ImageSize(200, 100);
		String imagePath = photos;
		Bitmap image = ImageLoader.getInstance().loadImageSync(imagePath,
				targetSize);
		mImageView.setImageBitmap(image);
		
		
		JRectangle rectangle = new JRectangle();
		SFrame frame2 = new SFrame(new SSPoint(330, 50),new SSize(400, 500));
		rectangle.setFrame(frame2);
		ItemRectangleView itemRectangleView = new ItemRectangleView(context, jTemplate, rectangle, frame2);
		itemRectangleView.setImagePath(imagePath);
		UIUtils.addView(this, itemRectangleView, frame2, 1);
		
		ImageView imageView = new ImageView(context);
		imageView.setBackgroundResource(R.drawable.sample1);
		SFrame frame = new SFrame();
		frame.setPoint(new SSPoint(0, 0));
		frame.setSize(jTemplate.getSize());
		UIUtils.addView(this, imageView, frame, 1);
	}
}
