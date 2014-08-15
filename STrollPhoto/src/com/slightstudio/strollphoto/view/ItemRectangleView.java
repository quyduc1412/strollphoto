package com.slightstudio.strollphoto.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.slightstudio.common.Const;
import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.JRectangle;
import com.slightstudio.strollphoto.model.SFrame;
import com.slightstudio.strollphoto.model.STemplate;

public class ItemRectangleView extends ItemImageView {
		

	private Path mPath;
	private Paint mPaint;
	
	private Path mBorderPath;
	private Paint mBorderPaint;
	
	private Path mBgPath;
	private Paint mBgPaint;
	
	private float mBorderWidth;
	private float mStrokeWidth;
	
	private boolean mHighlight;
	
	private JRectangle mRectangle;
	private float mLeft;
	private float mTop;
	private float mRight;
	private float mBottom;
	
	private Rect mContainBounds;
	
	public ItemRectangleView(Context context) {
		super(context);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public ItemRectangleView(Context context, STemplate template, JRectangle rectangle, SFrame bounds) {
		super(context);
		this.mRectangle = rectangle;
		
		this.mBounds = bounds;
		
		mRatio = template.getRatio();
		
		SFrame frame = rectangle.getFrame();
		mLeft = (float)((bounds.getSize().getWidth() - frame.getSize().getWidth()) / 2 * mRatio);
		mTop = (float)((bounds.getSize().getHeight() - frame.getSize().getHeight()) / 2 * mRatio);
		mRight = (float)(mLeft + frame.getSize().getWidth() * mRatio);
		mBottom = (float)(mLeft + frame.getSize().getHeight() * mRatio);
		
		mStrokeWidth = (float)(rectangle.getBorderWidth() * mRatio);
		mBorderWidth = rectangle.getBorderWidth();
		
		mPath = new Path();
		mPath.setFillType(FillType.INVERSE_WINDING);
		mPath.addRect(mLeft, mTop, mRight, mBottom, Path.Direction.CW);
		
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
	    mPaint.setColor(Color.WHITE);
	    
	    mBgPath = new Path();
	    mBgPath.addRect(mLeft, mTop, mRight, mBottom, Path.Direction.CW);

	    mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    mBgPaint.setStyle(Paint.Style.FILL);

		mBorderPath = new Path();
		mBorderPath.addRect(mLeft + mStrokeWidth/2, mTop + mStrokeWidth/2, 
				mRight - mStrokeWidth/2, mBottom - mStrokeWidth/2, Path.Direction.CW);

	    mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mBorderPaint.setStyle(Paint.Style.STROKE);
	    
	    if (mBorderWidth > 0) {
			mBorderPaint.setStrokeWidth(mStrokeWidth);
	    	if (rectangle.getBorderColor() != null) {
	    		mBorderPaint.setColor(UIUtils.color(rectangle.getBorderColor()));
	    	} else {
	    		mBorderPaint.setColor(Color.WHITE);
	    	}
	    }
	    
	    if (android.os.Build.VERSION.SDK_INT >= 11) {
	         setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	    }
	    
	    if (rectangle.getSkewAngle() != 0) {
	    	Matrix matrix = new Matrix();
	    	matrix.postRotate((float)rectangle.getSkewAngle(), 
	                   (mRight + mLeft)/2, 
	                   (mBottom + mTop)/2);
	    	mPath.transform(matrix);
	    }
	}
	
	
	public void setBorderThickness(int thickness) {
		Log.d(Const.DEBUG_TAG, "Rectangle#setBorderThickness()");
		
		mPath = new Path();
		mPath.setFillType(FillType.INVERSE_WINDING);
		mPath.addRect(mLeft + thickness, mTop + thickness, mRight - thickness, mBottom - thickness, Path.Direction.CW);
		invalidate();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.drawPath(mBgPath, mBgPaint);
	    super.dispatchDraw(canvas);
	    canvas.drawPath(mPath, mPaint);
	    
	    if (mBorderWidth > 0) {
	    	canvas.drawPath(mBorderPath, mBorderPaint);
	    }
	}
	
	@Override
	public void setHighlight(boolean enable) {
		if (enable != mHighlight) {
			mHighlight = enable;
			if (enable) {
				mBorderPaint.setColor(UIUtils.color("4eb7cd"));
				mBorderWidth = (int)UIUtils.dp2px(1);
				mStrokeWidth = (float)(mBorderWidth * mRatio);
			} else {
				mBorderWidth = mRectangle.getBorderWidth();
				mStrokeWidth = (float)(mBorderWidth * mRatio);
				if (mBorderWidth > 0) {
					mBorderPaint.setColor(UIUtils.color(mRectangle.getBorderColor()));
				}
			}
			mBorderPath.reset();
			mBorderPath.addRect(mLeft + mStrokeWidth/2, mTop + mStrokeWidth/2, 
					mRight - mStrokeWidth/2, mBottom - mStrokeWidth/2, Path.Direction.CW);
			mBorderPaint.setStrokeWidth(mStrokeWidth);
			invalidate();
		}
	}
	
	@Override
	public boolean isHighlight() {
		return mHighlight;
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (mContainBounds == null) {
			mContainBounds = new Rect();
			getGlobalVisibleRect(mContainBounds);
		}
		
		return mContainBounds.contains(x, y);
	}
}
