package com.slightstudio.strollphoto.view;

import java.io.IOException;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.slightstudio.common.ImageUtils;
import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.model.JCircle;
import com.slightstudio.strollphoto.model.JTemplate;

public class ItemCircleView extends ItemImageView {

	private Path mPath;
	private Paint mPaint;

	private Path mBorderPath;
	private Paint mBorderPaint;

	private Path mBgPath;
	private Paint mBgPaint;

	private float mRadius;

	private JCircle mCircle;
	private float mBorderWidth;
	private float mStrokeWidth;
	private float mCenterX;
	private float mCenterY;

	private boolean mHighlight;

	private Rect mContainBounds;
	
	public ItemCircleView(Context context) {
		super(context);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public ItemCircleView(Context context, JTemplate template, JCircle circle) {
		super(context);
		this.mCircle = circle;

		mRatio = template.getRatio();

		mBounds = circle.getFrame();

		mCenterX = (float) (mBounds.getSize().getWidth() * mRatio) / 2;
		mCenterY = (float) (mBounds.getSize().getHeight() * mRatio) / 2;
		mRadius = (float) (circle.getRadius() * mRatio);

		mStrokeWidth = (float) (circle.getBorderWidth() * mRatio);
		mBorderWidth = circle.getBorderWidth();

		mPath = new Path();
		mPath.setFillType(FillType.INVERSE_WINDING);
		mPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
		mPaint.setARGB(0, 0, 0, 0);

		mBgPath = new Path();
		mBgPath.addCircle(mCenterX, mCenterY, mRadius - mStrokeWidth / 2,
				Path.Direction.CW);

		mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBgPaint.setStyle(Paint.Style.FILL);

		mBorderPath = new Path();
		mBorderPath.addCircle(mCenterX, mCenterY, mRadius - mStrokeWidth / 2,
				Path.Direction.CW);

		mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBorderPaint.setStyle(Paint.Style.STROKE);

		if (mBorderWidth > 0) {
			mBorderPaint.setStrokeWidth(mStrokeWidth);
			if (circle.getBorderColor() != null) {
				mBorderPaint.setColor(UIUtils.color(circle.getBorderColor()));
			} else {
				mBorderPaint.setColor(Color.WHITE);
			}
		}
	}

	public void setBorderThickness(int thickness) {
		thickness = thickness / 4;
		mPath = new Path();
		mPath.setFillType(FillType.INVERSE_WINDING);
		mPath.addCircle(mCenterX, mCenterY, mRadius - (float)(thickness * mRatio),
				Path.Direction.CW);
		mBorderPath.reset();
		mStrokeWidth = (float) ((mCircle.getBorderWidth() + thickness) * mRatio);
		mBorderPath.addCircle(mCenterX, mCenterY, mRadius - mStrokeWidth
				/ 2, Path.Direction.CW);
		mBorderPaint.setStrokeWidth(mStrokeWidth);
		invalidate();
	}
	
	
	public void setColor(int color) {
		mBorderPaint.setColor(color);
		invalidate();
	}
	
	public void setImage(Context context, String asset) {
		mCircle.setBorderColor("00000000");
		mBorderPaint.setColor(Color.TRANSPARENT);
		this.invalidate();
		
		try {
			Drawable drawable = Drawable.createFromStream(context.getAssets()
					.open(asset), null);
			BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(),
					ImageUtils.drawableToBitmap(drawable));
			bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT,
					Shader.TileMode.REPEAT);

			getImageView().setBackgroundDrawable(bitmapDrawable);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setHighlight(boolean enable) {
		if (enable != mHighlight) {
			mHighlight = enable;
			if (enable) {
				mBorderPaint.setColor(UIUtils.color("4eb7cd"));
				mBorderWidth = (int) UIUtils.dp2px(1);
				mStrokeWidth = (float) (mBorderWidth * mRatio);
			} else {
				mBorderWidth = mCircle.getBorderWidth();
				mStrokeWidth = (float) (mBorderWidth * mRatio);
				if (mBorderWidth > 0) {
					mBorderPaint.setColor(UIUtils.color(mCircle
							.getBorderColor()));
				}
			}
			mBorderPath.reset();
			mBorderPath.addCircle(mCenterX, mCenterY, mRadius - mStrokeWidth
					/ 2, Path.Direction.CW);
			mBorderPaint.setStrokeWidth(mStrokeWidth);
			invalidate();
		}
	}

	@Override
	public boolean isHighlight() {
		return mHighlight;
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
	public boolean contains(int x, int y) {
		if (mContainBounds == null) {
			mContainBounds = new Rect();
			getGlobalVisibleRect(mContainBounds);
		}

		return Math.pow(x - mContainBounds.centerX(), 2)
				+ Math.pow(y - mContainBounds.centerY(), 2) < Math.pow(mRadius,
				2);
	}
}
