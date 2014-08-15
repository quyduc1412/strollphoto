package com.slightstudio.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.slightstudio.application.App;
import com.slightstudio.strollphoto.model.SFrame;
import com.slightstudio.strollphoto.model.SSPoint;
import com.slightstudio.strollphoto.model.SSize;
import com.slightstudio.strollphoto.model.STemplate;

public class UIUtils {

	public static DisplayMetrics getMetrics() {
		return App.getContext().getResources().getDisplayMetrics();
	}

	
	public static int navigationBarHeight() {
		Context context = App.getContext();
		Resources resources = context.getResources();

		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}
	
	public static int statusBarHeight() {
		Context context = App.getContext();
		Resources resources = context.getResources();
		
		int result = 0;
		int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = resources.getDimensionPixelSize(resourceId);
		}
		return result;
	}
	
	public static int screenWidth() {
		return getMetrics().widthPixels;
	}

	public static int screenHeight() {
		return getMetrics().heightPixels;
	}
	
	/**
	 *  Calculate ActionBar height
	 */
	public static void addViewFit(ViewGroup parent, View view) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		parent.addView(view, params);
	}
	
	public static void addViewFill(ViewGroup parent, View view) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		parent.addView(view, params);
	}
	
	public static float dp2px(int dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getMetrics());	
	}
	
	public static int color(String rgb) {
		return Color.parseColor(String.format("#%s", rgb));
	}
	
	public static Bitmap loadBitmapFromView(View v) {
	    Bitmap bitmap = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);                
	    Canvas ccanvas = new Canvas(bitmap);
	    v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
	    v.draw(ccanvas);
	    return bitmap;
	}
	public static void addView(ViewGroup parent, View view, SFrame frame, double ratio) {		

		int newX = (int) (frame.getPoint().getX() * ratio);
		int newY = (int) (frame.getPoint().getY() * ratio);

		int newWidth = (int) (frame.getSize().getWidth() * ratio);
		int newHeight = (int) (frame.getSize().getHeight() * ratio);
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(newWidth, newHeight);
		params.leftMargin = newX;
		params.topMargin = newY;
		params.gravity = Gravity.TOP | Gravity.LEFT;

		parent.addView(view, params);
	}
	
	public static SFrame calculateFrameBaseOnRatio(STemplate jTemplate) {
		int bottomBarHeight = (int) UIUtils.dp2px(60);
		int actionBarHeight = 0;
		int statusBarHeight = UIUtils.statusBarHeight();

		int padding = 0;

		int maxWidth = UIUtils.screenWidth() - padding * 2;
		int maxHeight = UIUtils.screenHeight() - actionBarHeight
				- statusBarHeight - bottomBarHeight - padding * 2;

		SSize designSize = jTemplate.getSize();
		double designWidth = designSize.getWidth();
		double designHeight = designSize.getHeight();

		SSize actualSize = new SSize();

		if (designHeight > designWidth) {
			// this is portrait template so the ratio should be w=2 / h=3
			if (maxWidth * 3 > maxHeight * 2) {
				actualSize.setHeight(maxHeight);
				actualSize.setWidth(maxHeight * 2 / 3);
			} else {
				actualSize.setWidth(maxWidth);
				actualSize.setHeight(maxWidth * 3 / 2);
			}
		} else if (designHeight == designWidth) {
			// this is square template so ratio is 1 we will take the small
			// one
			int min = Math.min(maxWidth, maxHeight);
			actualSize.setWidth(min);
			actualSize.setHeight(min);
		} else {
			// this is landscape template so the ratio should be w=3 / h=2
			if (maxHeight * 3 > maxWidth * 2) {
				actualSize.setWidth(maxWidth);
				actualSize.setHeight(maxWidth * 2 / 3);
			} else {
				actualSize.setHeight(maxHeight);
				actualSize.setWidth(maxHeight * 3 / 2);

			}
		}

		// we need ratio to calculate frame for each item inside the
		// template
		// later
		double ratio = actualSize.getWidth()
				/ jTemplate.getSize().getWidth();
		jTemplate.setRatio(ratio);

		double x = (maxWidth - actualSize.getWidth()) / 2;
		double y = (maxHeight - actualSize.getHeight()) / 2;

		return new SFrame(new SSPoint(x / ratio, y / ratio), new SSize(
				actualSize.getWidth() / ratio, actualSize.getHeight()
						/ ratio));
	}
}
