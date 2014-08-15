package com.slightstudio.common;

import android.app.Application;
import android.content.Context;

public class App extends Application {
	
	private static Context sContext;
//	private static DisplayImageOptions sOptions;
	
	@Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        
//        JIndicator.init(sContext);
        
        FileUtils.clearCacheDir();
        
//        sOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.ic_launcher)
//				.showImageOnFail(R.drawable.no_image)
//				.bitmapConfig(Bitmap.Config.RGB_565)
//				.cacheInMemory(true)
//				.considerExifParams(true)
//				.imageScaleType(ImageScaleType.EXACTLY)
//				.build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//		        .memoryCacheSize(5 * 1024 * 1024)
//		        .diskCacheSize(50 * 1024 * 1024)
//		        .diskCacheFileCount(100)
//		        .defaultDisplayImageOptions(sOptions)
//		        .build();
//        ImageLoader.getInstance().init(config);
//        
//        // override Default Font for our app
//        FontUtils.setDefaultFont(this, "DEFAULT", "fonts/RobotoSlab_Regular.ttf");
//        FontUtils.setDefaultFont(this, "MONOSPACE", "fonts/RobotoSlab_Regular.ttf");
//        FontUtils.setDefaultFont(this, "SANS_SERIF", "fonts/RobotoSlab_Regular.ttf");
    }
	
	public static Context getContext() {
		return sContext;
	}
//	
//	public static DisplayImageOptions defaultImageOptions() {
//		return sOptions;
//	}
//	
	@Override
	public void onTerminate() {
		super.onTerminate();
		
        FileUtils.clearCacheDir();
	}
}
