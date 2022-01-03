package com.freztal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WallpaperActivity extends AppCompatActivity {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setLiveWP();
	}

	protected void setLiveWP() {
		startActivity(new Intent(
						  "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER"));
		finish();
	}

}
