package com.slightstudio.strollphoto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.slightstudio.common.UIUtils;
import com.slightstudio.strollphoto.model.JFrame;
import com.slightstudio.strollphoto.model.JSize;
import com.slightstudio.strollphoto.model.JTemplate;
import com.slightstudio.strollphoto.view.TemplateView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
					JTemplate jTemplate = new JTemplate();
					jTemplate.setRatio(1);
					jTemplate.setSize(new JSize().setWidth(
							mainLayout.getWidth()).setHeight(
							mainLayout.getHeight()));
					TemplateView templateView = new TemplateView(getActivity(),
							jTemplate);
					JFrame frame = new JFrame(0, 0, jTemplate.getSize()
							.getWidth(), jTemplate.getSize().getHeight());
					UIUtils.addView(mainLayout, templateView, frame,
							jTemplate.getRatio());
				}
			});
		}
	}

}
