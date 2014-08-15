package com.slightstudio.strollphoto.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.polites.android.GestureImageView;
import com.slightstudio.common.Const;
import com.slightstudio.common.ImageUtils;
import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.SFrame;
import com.slightstudio.strollphoto.model.STemplate;


public abstract class ItemImageView extends FrameLayout implements OnGestureListener, OnTouchListener {

	private GestureImageView mImageView;
	private GestureDetector mGestureDetector;

	private Context mContext;
	private Drawable mImage;
	private String mImagePath;
	
	protected SFrame mBounds;
	protected double mRatio;
	
	private boolean onlySingleTap;
	private boolean mLongClicked;
	
	@SuppressLint("NewApi")
	public ItemImageView(Context context) {
		super(context);
		mContext = context;
		
		mGestureDetector = new GestureDetector(context, this);
	}

	public GestureImageView getImageView() {
		return mImageView;
	}

	public void setImageView(GestureImageView imageView) {
		this.mImageView = imageView;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int x = (int)event.getRawX();
		int y = (int)event.getRawY();
		
		if (mLongClicked || contains(x, y)) {
			return super.dispatchTouchEvent(event);
		}
		return false;
	}
	
	public void removeImage() {
		mImagePath = null;
		mImage = null;
		mImageView.setImageBitmap(null);						
		mImageView.invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(Const.DEBUG_TAG, "Touch Event fired");
		onlySingleTap = true;
		mGestureDetector.onTouchEvent(event);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.d(Const.DEBUG_TAG, "Touch Down");
		mLongClicked = false;
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.d(Const.DEBUG_TAG, "Fling");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		if (!onlySingleTap) {
			Log.d(Const.DEBUG_TAG, "Long Pressed");
			
			int eventX = (int)event.getRawX();
			int eventY = (int)event.getRawY();
			
			Log.d(Const.DEBUG_TAG, "X Pressed: " + eventX);
			Log.d(Const.DEBUG_TAG, "Y Pressed: " + eventY);
			
	
			if (contains(eventX, eventY)) {
				mLongClicked = true;
				
				mImage = mImageView.getDrawable();
		
				// we will temporarily hide image and set it back when user touch up
				mImageView.setImageBitmap(null);						
				mImageView.invalidate();
				
//				if (mContext instanceof ItemViewLongTouchListener) {
//					((ItemViewLongTouchListener)mContext).onLongTouchDown(this, ImageUtils.cloneDrawable(mImage), event);	
//				}
			}
		}
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d(Const.DEBUG_TAG, "On Scroll");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.d(Const.DEBUG_TAG, "On Show Press");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.d(Const.DEBUG_TAG, "Signle Tap");
//		if (mContext instanceof ItemViewLongTouchListener) {
//			((ItemViewLongTouchListener)mContext).onSingleTapUp(this, e);	
//		}
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		onlySingleTap = false;
		mGestureDetector.onTouchEvent(event);
		if (mLongClicked) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				Log.d(Const.DEBUG_TAG, "Long Pressed Up");
				
				mImageView.setImageDrawable(mImage);						
				
//				if (mContext instanceof ItemViewLongTouchListener) {
//					((ItemViewLongTouchListener)mContext).onLongTouchUp(this, event);	
//				}
				break;
			case MotionEvent.ACTION_MOVE:
				// long press move
//				if (mContext instanceof ItemViewLongTouchListener) {
//					((ItemViewLongTouchListener)mContext).onLongTouchMove(this, event);	
//				}
			}
		}
		return false;
	}
	
	/**
	 * This method need to implement in inheritance class to draw the highlight 
	 * when user move a photo over this view to swap these two photos
	 *  
	 * @param enable
	 */
	public void setHighlight(boolean enable) {
	}
	
	public boolean isHighlight() {
		return false;
	}

	public String getImagePath() {
		return mImagePath;
	}
	
	public void setImagePath(String imagePath) {
		removeAllViews();
		
		Log.d(Const.DEBUG_TAG, "IMAGE: " + imagePath);
		this.mImagePath = imagePath;

		mImageView = new GestureImageView(mContext);		
		mImageView.setScaleType(ScaleType.CENTER_CROP);
		mImageView.setOnTouchListener(this);
		UIUtils.addViewFill(this, getImageView());
		mImageView.invalidate();
		
		int targetWidth = (int) (mBounds.getSize().getWidth() * mRatio);
		int targetHeight = (int) (mBounds.getSize().getHeight() * mRatio);
		
		ImageSize targetSize = new ImageSize(targetWidth, targetHeight);
		Bitmap image = ImageLoader.getInstance().loadImageSync(mImagePath, targetSize);
		mImageView.setImageBitmap(image);
	}
	
	public SFrame getBounds() {
		return mBounds;
	}
	public abstract boolean contains(int x, int y);
}
