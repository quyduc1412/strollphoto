package com.slightstudio.strollphoto.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.slightstudio.strollphoto.R;

public class ShareActivity extends Activity implements OnClickListener {
	private ImageView sWechat, sFriends, sSina, sFacebook, sTwitter, sEmail,
			sCloud, sMore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		iniUI();
	}

	private void iniUI() {
		sWechat = (ImageView) findViewById(R.id.sWeChat);
		sFriends = (ImageView) findViewById(R.id.sFriends);
		sSina = (ImageView) findViewById(R.id.sSina);
		sFacebook = (ImageView) findViewById(R.id.sFacebook);
		sTwitter = (ImageView) findViewById(R.id.sTwitter);
		sEmail = (ImageView) findViewById(R.id.sEmail);
		sCloud = (ImageView) findViewById(R.id.sCloudAlbum);
		sMore = (ImageView) findViewById(R.id.sMore);
		sWechat.setOnClickListener(this);
		sSina.setOnClickListener(this);
		sFacebook.setOnClickListener(this);
		sTwitter.setOnClickListener(this);
		sEmail.setOnClickListener(this);
		sCloud.setOnClickListener(this);
		sMore.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sWeChat:

			break;
		case R.id.sFriends:

			break;
		case R.id.sSina:

			break;
		case R.id.sFacebook:

			break;
		case R.id.sTwitter:

			break;
		case R.id.sEmail:

			break;
		case R.id.sCloudAlbum:

			break;
		case R.id.sMore:

			break;
		default:
			break;
		}

	}
}
