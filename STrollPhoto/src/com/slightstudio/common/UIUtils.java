package com.slightstudio.common;

import com.slightstudio.strollphoto.model.JFrame;

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
	public static void addView(ViewGroup parent, View view, JFrame frame, double ratio) {		

		int newX = (int) (frame.getX() * ratio);
		int newY = (int) (frame.getY() * ratio);

		int newWidth = (int) (frame.getWidth() * ratio);
		int newHeight = (int) (frame.getHeight() * ratio);
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(newWidth, newHeight);
		params.leftMargin = newX;
		params.topMargin = newY;
		params.gravity = Gravity.TOP | Gravity.LEFT;

		parent.addView(view, params);
	}
}
