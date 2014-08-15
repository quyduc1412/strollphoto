package com.slightstudio.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.res.AssetManager;
import android.os.Environment;

import com.slightstudio.application.App;

public class FileUtils {

	public static final String INTERNAL_FILE_PROTOCOL = "file://";
	private static final int BUFFER_SIZE = 1024;

	public static String read(InputStream input) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int b = input.read(buffer); b != -1; b = input.read(buffer)) {
			baos.write(buffer, 0, b);
		}

		return new String(baos.toByteArray());
	}

	public static String getInternalPath(String path) {
		return INTERNAL_FILE_PROTOCOL + path;
	}

	/**
	 * Allow user to read string from asset file
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readFromAsset(String path) throws IOException {
		AssetManager assets = App.getContext().getAssets();

		InputStream input = assets.open(path);
		String content = read(input);

		return content;
	}

	public static List<String> getAllAssetsWithPath(String path)
			throws IOException {
		List<String> allAssets = new ArrayList<String>();

		AssetManager assets = App.getContext().getAssets();
		String[] children = assets.list(path);

		for (String file : children) {
			StringBuffer asset = new StringBuffer();
			asset.append(path).append("/").append(file);
			allAssets.add(asset.toString());
		}

		return allAssets;
	}

	public static void clearCacheDir() {
		String externalDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String cacheDir = String.format("%s/%s/cache", externalDir,
				Const.JMAGAZINE);
		File mediaStorageDir = new File(cacheDir);
		if (mediaStorageDir.exists()) {
			mediaStorageDir.delete();
		}
	}

	public static File generateTempImageFile() {
		String externalDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String cacheDir = String.format("%s/%s/cache", externalDir,
				Const.JMAGAZINE);
		File mediaStorageDir = new File(cacheDir);
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				// TODO: show the message to user
			}
		}

		File temp = new File(String.format("%s/%s.jpg",
				mediaStorageDir.getAbsolutePath(), System.currentTimeMillis()));
		return temp;
	}

	public static File generateImageFile() {
		String externalDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String jMagazineDir = String.format("%s/%s/", externalDir,
				Const.JMAGAZINE);
		File mediaStorageDir = new File(jMagazineDir);
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				// TODO: show the message to user
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		File temp = new File(String.format("%s/%s.jpg",
				mediaStorageDir.getAbsolutePath(), sdf.format(new Date())));
		return temp;
	}

	public static String getAssetPath(String name) {
		return String.format("assets://%s", name);
	}

	public static String getTemplateAssetPath(String name) {
		return String.format("assets://templates/%s", name);
	}

	public static String getStickerAssetPath(String name) {
		return String.format("assets://stickies/%s", name);
	}

	public static boolean downloadFile(String urlString) {
		int count = 0;
		URL url;
		InputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		try {
			File root = android.os.Environment.getExternalStorageDirectory();
			File dir = new File(root.getAbsolutePath() + "/" + Const.DIR_FONT);
			if (dir.exists() == false) {
				dir.mkdirs();
			}
			String file_url = urlString;
			String[] url_split = file_url.split("/");
			String fontName = url_split[url_split.length - 1];
			fontName = fontName.replace("-", "_");
			url = new URL(urlString);
			inputStream = new BufferedInputStream(url.openStream());
			File file = new File(dir, fontName);
			OutputStream output = new FileOutputStream(file);
			outputStream = new BufferedOutputStream(output);
			byte data[] = new byte[512];
			while ((count = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, count);
			}
			outputStream.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtils.close(inputStream);
			FileUtils.close(outputStream);
		}
		return true;
	}

	public static void close(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(OutputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
