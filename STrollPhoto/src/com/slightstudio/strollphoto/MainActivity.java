package com.slightstudio.strollphoto;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.JSize;
import com.slightstudio.strollphoto.model.SFrame;
import com.slightstudio.strollphoto.model.SSPoint;
import com.slightstudio.strollphoto.model.SSize;
import com.slightstudio.strollphoto.model.STemplate;
import com.slightstudio.strollphoto.view.TemplateView;

public class MainActivity extends ActionBarActivity {

	private DisplayImageOptions sOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		sOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.icon_cloudalbum)
				.bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true)
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).memoryCacheSize(5 * 1024 * 1024)
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
				.defaultDisplayImageOptions(sOptions).build();
		ImageLoader.getInstance().init(config);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private FrameLayout mainLayout;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			mainLayout = (FrameLayout) view
					.findViewById(R.id.editorContentLayout);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			mainLayout.post(new Runnable() {
				@Override
				public void run() {
					STemplate jTemplate = new STemplate();
					jTemplate.setRatio(1);
					jTemplate.setSize(new SSize().setWidth(
							mainLayout.getWidth()).setHeight(
							mainLayout.getHeight()));
					TemplateView templateView = new TemplateView(getActivity(),
							jTemplate);
					SFrame frame = UIUtils.calculateFrameBaseOnRatio(jTemplate);
					UIUtils.addView(mainLayout, templateView, frame,
							jTemplate.getRatio());
				}
			});
		}

		
	}

}
