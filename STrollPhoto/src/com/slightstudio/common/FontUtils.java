package com.slightstudio.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;

import com.slightstudio.strollphoto.R;

public class FontUtils {

	private static Map<String, Typeface> map = new HashMap<String, Typeface>();

	public static Typeface getTypeface(String name) {
		Typeface typeface = map.get(name);
		if (typeface == null) {
			String fontName = name.replace("-", "_");
			String ttf = String.format("%s.ttf", fontName);
			String otf = String.format("%s.otf", fontName);
			File root = android.os.Environment.getExternalStorageDirectory();
			try {
				List<String> fonts = listFontFromSDCard(Const.DIR_FONT, false);
				if (fonts.contains(ttf)) {
					File dir = new File(root.getAbsolutePath() + "/"
							+ Const.DIR_FONT + "/" + ttf);
					typeface = Typeface.createFromFile(dir);
				} else if (fonts.contains(otf)) {
					File dir = new File(root.getAbsolutePath() + "/"
							+ Const.DIR_FONT + "/" + otf);
					typeface = Typeface.createFromFile(dir);
				}
				if (typeface != null) {
					map.put(name, typeface);
				}
			} catch (IOException e) {
				e.printStackTrace();
//				JToast.error(R.string.error_loading_font);
			}

		}
		return typeface;
	}

	public static void setDefaultFont(Context context,
			String staticTypefaceFieldName, String fontAssetName) {
		final Typeface regular = Typeface.createFromAsset(context.getAssets(),
				fontAssetName);
		replaceFont(staticTypefaceFieldName, regular);
	}

	private static void replaceFont(String staticTypefaceFieldName,
			final Typeface newTypeface) {
		try {
			final Field StaticField = Typeface.class
					.getDeclaredField(staticTypefaceFieldName);
			StaticField.setAccessible(true);
			StaticField.set(null, newTypeface);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static float getStandardFontSize(double size) {
		double ratio = UIUtils.screenWidth() / 960.0f;
		if (ratio > 1) {
			return (float) (size * ratio);
		}
		return (float) size;
	}

	public static List<String> listFiles(String dirFrom, Context context)
			throws IOException {
		Resources res = context.getResources();
		AssetManager am = res.getAssets();
		String fileList[] = am.list(dirFrom);
		List<String> list = new ArrayList<String>();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				String file = fileList[i];
				String fileName = new String();
				if (file.contains("ttf")) {
					fileName = fileList[i].replace(".ttf", "");
				} else if (file.contains("otf")) {
					fileName = fileList[i].replace(".otf", "");
				}
				list.add(fileName);
			}
		}
		return list;
	}

	public static List<String> listFontFromSDCard(String dirFrom, boolean is)
			throws IOException {
		List<String> listFonts = new ArrayList<String>();
		File root = android.os.Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/" + dirFrom);
		File listFont[] = dir.listFiles();
		if (listFont != null) {
			for (int i = 0; i < listFont.length; i++) {
				String file = listFont[i].getName();
				if (is) {
					String fileName = new String();
					if (file.contains("ttf")) {
						fileName = file.replace(".ttf", "");
					} else if (file.contains("otf")) {
						fileName = file.replace(".otf", "");
					}
					listFonts.add(fileName);
				} else {
					listFonts.add(file);
				}
			}
		}
		return listFonts;
	}
	
	public static List<Integer> getColors() {
		String[] list = new String[] { "#a90d3d", "#c80a36", "#ea0e0e",
				"#f24a1b", "#f66313", "#fcc207", "#fae500", "#fabc97",
				"#fad495", "#f00e7a", "#f1528c", "#f5729c", "#e38597",
				"#f2b5b0", "#6400b0", "#9868cc", "#9d8edf", "#c600b9",
				"#ad1dba", "#ce64de", "#e57bdd", "#312c54", "#070f98",
				"#0048be", "#0033d4", "#0084cc", "#4873b8", "#72b1c2",
				"#a5c1cf", "#00646c", "#009f9f", "#00bdb6", "#00c3d9",
				"#69d4da", "#007e6d", "#079c5c", "#009d3e", "#0cbf85",
				"#54ab42", "#a3de22", "#c8a82d", "#573222", "#a25d18",
				"#a77751", "#6d5042", "#8c6e56", "#bd987b", "#a7acc0",
				"#e3e4df", "#dfe1d6", "#e0e5df", "#ffffff", "#000000" };
		List<Integer> listColor = new ArrayList<Integer>();
		for (int i = 0; i < list.length; i++) {
			listColor.add(Color.parseColor(list[i]));
		}
		return listColor;
	}
}
