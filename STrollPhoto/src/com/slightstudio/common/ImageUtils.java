package com.slightstudio.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;

public class ImageUtils {

	public static final int BUFFER_SIZE = 1024 * 8;
	
	public static Bitmap getBitmap(Context ctx, String path) {
		Bitmap bitmap = null;
		try {
			InputStream is = ctx.getAssets().open(path);
			bitmap = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	public static Bitmap cloneBitmap(Bitmap src) {
		if (src == null) return null;
		
		// we should provide a matrix here to make sure new Bitmap will be another object
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), new Matrix(), false);
	}
	
	public static Drawable cloneDrawable(Drawable src) {
		if (src == null) return null;
		return src.getConstantState().newDrawable();
	}

	public static Bitmap getBitmap(File file) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap getBitmap(String path) {
		return getBitmap(new File(path));
	}

	public static Bitmap rotateAndCrop(Bitmap original, float degree, int x,
			int y, int width, int height, Camera.Size size) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);

		// rotate bitmap
		Bitmap bitmap = Bitmap.createBitmap(original, 0, 0,
				original.getWidth(), original.getHeight(), matrix, true);

		// Picture is rotate by 90 degress
		float compare = ((float) bitmap.getWidth() / (float) bitmap.getHeight())
				- ((float) size.height / (float) size.width);
		if (compare > 0) {
			// crop width
			int pHeight = bitmap.getHeight();
			int pWidth = pHeight * size.height / size.width;

			Bitmap cropped = Bitmap.createBitmap(bitmap,
					(bitmap.getWidth() - pWidth) / 2, 0, pWidth, pHeight);

			bitmap.recycle();

			bitmap = cropped;
		} else if (compare < 0) {
			// crop height
			int pWidth = bitmap.getWidth();
			int pHeight = pWidth * size.width / size.height;

			Bitmap cropped = Bitmap.createBitmap(bitmap, 0,
					(bitmap.getHeight() - pHeight) / 2, pWidth, pHeight);

			bitmap.recycle();

			bitmap = cropped;
		}

		int pWidth = width;
		int pHeight = width * size.width / size.height;
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, pWidth,
				pHeight, true);

		bitmap.recycle();

		// crop bitmap
		Bitmap result = Bitmap.createBitmap(scaledBitmap, x, y, width, height);

		scaledBitmap.recycle();
		bitmap.recycle();

		return result;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		}

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	public static void writeExternalToCache(Bitmap bitmap, File file) {
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			final BufferedOutputStream bos = new BufferedOutputStream(fos,
					BUFFER_SIZE);
			bitmap.compress(CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Bitmap rorateBitmap(Bitmap bm, int rotate) {
		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);
		return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(),
				matrix, true);
	}
	
	public static Uri addImageToGallery(Context context, String filepath, String title, String description) {    
	    ContentValues values = new ContentValues();
	    values.put(Media.TITLE, title);
	    values.put(Media.DESCRIPTION, description); 
	    values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());
	    values.put(Images.Media.MIME_TYPE, "image/jpeg");
	    values.put(MediaStore.MediaColumns.DATA, filepath);

	    return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
	}

	public static Bitmap invertColor(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int length = width * height;
		int[] array = new int[length];
		
		bitmap.getPixels(array, 0, width, 0, 0, width, height);
		
		int R, G, B, A;
		for (int i = 0; i < length; i++) {
			A = Color.alpha(array[i]);
			R = 255 - Color.red(array[i]);
            G = 255 - Color.green(array[i]);
            B = 255 - Color.blue(array[i]);
			
            array[i] = Color.argb(A, R, G, B);
		}
		
		Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		output.setPixels(array, 0, width, 0, 0, width, height);
		return output;
	}
}
