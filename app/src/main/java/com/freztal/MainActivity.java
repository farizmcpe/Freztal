package com.freztal;

import android.app.*;
import android.os.*;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import java.io.File;
import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.net.Uri;
import android.widget.ImageView;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	Button Button1;
	Button google;
	Button Button3;
	Button Button4;
	Button Button5;
	Button ldiscord;
	Button caller;
	Button openapk;
	Button about;
	Button toolmcfrz;
	Button mcdownload;
	Button rcm;
	Button tmcbot;
	Button ftc;
	Button cl;
	Button sb;
	Button dc;
	Button atu;
	ImageView logo;
	Button d1;
	Button r;
	Button exit;
	Button gi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		String fc = "Download/Freztal_1.2_By_FARIZ_MCPE.apk";

		File f = new File(Environment.getExternalStorageDirectory(), fc);
		if (!f.exists()) {
			Intent ca = new Intent(getApplicationContext(), CrackedActivity.class);
			startActivity(ca);
		} else {
			setContentView(R.layout.main);
			}
		Toast.makeText(getApplication(), "Welcome To Freztal!", Toast.LENGTH_SHORT).show();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

				Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
				String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
				requestPermissions(permissions, 1);
			}
		}
	
	
		Button1 = findViewById(R.id.settings);
		Button1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent in = new Intent(getApplicationContext(), SettingActivity.class);
					startActivity(in);
				}

			});
		google = findViewById(R.id.google);
		google.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent ga = new Intent(getApplicationContext(), GoogleActivity.class);
					startActivity(ga);
				}

			});
		Button3 = findViewById(R.id.flashlight);
		Button3.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent fa = new Intent(getApplicationContext(), FlashActivity.class);
					startActivity(fa);
				}

			});
		Button4 = findViewById(R.id.wallpaper);
		Button4.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent wa = new Intent(getApplicationContext(), WallpaperActivity.class);
					startActivity(wa);
				}

			});
		openapk = findViewById(R.id.openapk);
		openapk.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent wa = new Intent(getApplicationContext(), OpenApps.class);
					startActivity(wa);
				}

			});
		 caller = findViewById(R.id.caller);
		caller.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {

							Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
							String[] permissions = {Manifest.permission.CALL_PHONE};
							requestPermissions(permissions, 1);
							}
							}
					
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
							Toast.makeText(getApplication(), "Permission Denied!", Toast.LENGTH_SHORT).show();
						} else {

					Intent clr = new Intent(getApplicationContext(), CallActivity.class);
					startActivity(clr);
				}
				}
				}

			});
		toolmcfrz = findViewById(R.id.toolmcfrz);
		toolmcfrz.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

							Intent tmc = new Intent(getApplicationContext(), ToolMcFRZ.class);
							startActivity(tmc);
							} else {

					Intent gst = new Intent(getApplicationContext(), GrantActivity.class);
					startActivity(gst);
					
					
			
						
				}
				}
				
				
}
			});
		mcdownload = findViewById(R.id.mcdownload);
		mcdownload.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent mcdown = new Intent(getApplicationContext(), McActivity.class);
					startActivity(mcdown);
				}

			});
		rcm = findViewById(R.id.rcm);
		rcm.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent cm = new Intent(getApplicationContext(), RcmActivity.class);
					startActivity(cm);
				}

			});
		cl = findViewById(R.id.changelog);
		cl.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					Intent cl = new Intent(getApplicationContext(), ChangelogActivity.class);
					startActivity(cl);
				}

			});
		sb = findViewById(R.id.subscribe);
		sb.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View p1) {
					
				
		Intent se = new Intent(Intent.ACTION_VIEW);
		se.setData(Uri.parse("https://youtube.com/c/FARIZMCPE"));
		se.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		startActivity(se);
		}
		});
		dc = findViewById(R.id.discord);
		dc.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {


					Intent dd = new Intent(Intent.ACTION_VIEW);
					dd.setData(Uri.parse("https://discord.io/FARIZMCPEOFFICIAL"));
					dd.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(dd);
				}
			});
		atu = findViewById(R.id.atu);
		atu.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {


					Intent se = new Intent(Intent.ACTION_VIEW);
					se.setData(Uri.parse("https://farizmcpeofficial.blogspot.com/2021/09/toolmcfrz-updates.html?m=1"));
					se.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(se);
				}
			});
		d1 = findViewById(R.id.tmc1);
		d1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {


					Intent t1 = new Intent(Intent.ACTION_VIEW);
					t1.setData(Uri.parse("https://rekonise.com/download-infinite-toolbox-u6al3"));
					t1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(t1);
				}
			});
		logo = findViewById(R.id.logo);
		logo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {


					Intent lo = new Intent(Intent.ACTION_VIEW);
					lo.setData(Uri.parse("https://youtube.com/c/FARIZMCPE"));
					lo.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(lo);
				}
			});
		r = findViewById(R.id.restart);
		r.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {
                    
					finish();
					Handler handler= new Handler();
					handler.postDelayed(new Runnable(){
							@Override
							public void run()
							{
					Intent rs = new Intent(MainActivity.this, splash.class);
					startActivity(rs);
							}
						}, 3000);
				}
			});
		exit = findViewById(R.id.exit);
		exit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {

					finish();
				}
			});
		gi = findViewById(R.id.atbgui);
		gi.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View p1) {
					
		String dc = "tbgui/";
		File ex = new File(Environment.getExternalStorageDirectory(), "tbgui/ui_render.json");

		String fontfile = "https://github.com/farizmcpe/tmcgui/blob/main/font.ttf?raw=true";
		String stringsfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/strings.ttf?token=AVQA2V5X4HRE2HSPIGHBAODBZM6QY";
		String categoryfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_categories.json?token=AVQA2V5UT5H4TDMOXD2RTUDBZNEJK";
		String compatfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_combat.ttf?token=AVQA2VY7P34GDODUDQD2XTTBZM62K";
		String movementfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_movement.ttf?token=AVQA2V7AHYLQ37HQO7OYNETBZM7SI";
		String otherfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_other.ttf?token=AVQA2V3QFU5UAJML7SLOA5LBZM7YI";
		String renderfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_render.ttf?token=AVQA2V6OBV7YTGG36TT7XGTBZM74U";
		String worldfile = "https://raw.githubusercontent.com/farizmcpe/tmcgui/main/ui_world.ttf?token=AVQA2V5WHUXXJ2SI54UWAWLBZM774";

		File gui 
		= new File(Environment.getExternalStorageDirectory() + dc);

		if (!ex.exists()) {
			Toast.makeText(getApplication(), "Adding...", Toast.LENGTH_LONG).show();
			DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

			Uri uri = Uri.parse(fontfile);
			DownloadManager.Request request = new DownloadManager.Request(uri);

			request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			request.setAllowedOverRoaming(false);
			request.setTitle("Font.ttf");
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			request.allowScanningByMediaScanner();
			request.setDescription("Downloading File...");
			request.setDestinationInExternalPublicDir(dc, ("font") + ".ttf");

			downloadManager.enqueue(request);



			downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
			Uri s = Uri.parse(stringsfile);
			DownloadManager.Request st = new DownloadManager.Request(s);

			st.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			st.setAllowedOverRoaming(false);
			st.setTitle("Strings.json");
			st.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			st.allowScanningByMediaScanner();
			st.setDescription("Downloading File...");
			st.setDestinationInExternalPublicDir(dc, ("strings") + ".json");

			downloadManager.enqueue(st);




			Uri c = Uri.parse(compatfile);
			DownloadManager.Request co = new DownloadManager.Request(c);

			co.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			co.setAllowedOverRoaming(false);
			co.setTitle("Compat.json");
			co.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			co.allowScanningByMediaScanner();
			co.setDescription("Downloading File...");
			co.setDestinationInExternalPublicDir(dc, ("ui_compat") + ".json");

			downloadManager.enqueue(co);




			Uri m = Uri.parse(movementfile);
			DownloadManager.Request mo = new DownloadManager.Request(m);

			mo.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			mo.setAllowedOverRoaming(false);
			mo.setTitle("Movement.json");
			mo.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			mo.allowScanningByMediaScanner();
			mo.setDescription("Downloading File...");
			mo.setDestinationInExternalPublicDir(dc, ("ui_movement") + ".json");

			downloadManager.enqueue(mo);




			Uri o = Uri.parse(otherfile);
			DownloadManager.Request of = new DownloadManager.Request(o);

			of.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			of.setAllowedOverRoaming(false);
			of.setTitle("OtherFiles");
			of.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			of.allowScanningByMediaScanner();
			of.setDescription("Downloading File...");
			of.setDestinationInExternalPublicDir(dc, ("ui_other") + ".json");

			downloadManager.enqueue(of);



			downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
			Uri w = Uri.parse(worldfile);
			DownloadManager.Request wr = new DownloadManager.Request(w);

			wr.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			wr.setAllowedOverRoaming(false);
			wr.setTitle("World.json");
			wr.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			wr.allowScanningByMediaScanner();
			wr.setDescription("Downloading File...");
			wr.setDestinationInExternalPublicDir(dc, ("ui_world") + ".json");

			downloadManager.enqueue(wr);



			downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
			Uri cf = Uri.parse(categoryfile);
			DownloadManager.Request ca = new DownloadManager.Request(cf);

			ca.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			ca.setAllowedOverRoaming(false);
			ca.setTitle("Categories.json");
			ca.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			ca.allowScanningByMediaScanner();
			ca.setDescription("Downloading File...");
			ca.setDestinationInExternalPublicDir(dc, ("ui_categories") + ".json");

			downloadManager.enqueue(ca);



			downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
			Uri x = Uri.parse(renderfile);
			DownloadManager.Request xr = new DownloadManager.Request(x);

			xr.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
			xr.setAllowedOverRoaming(false);
			xr.setTitle("Render");
			xr.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			xr.allowScanningByMediaScanner();
			xr.setDescription("Downloading File...");
			xr.setDestinationInExternalPublicDir(dc, ("ui_render") + ".json");

			downloadManager.enqueue(xr);

			Toast.makeText(getApplication(), "Added New Gui", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplication(), "Already Added!", Toast.LENGTH_LONG).show();
				}
				}
			});
			
			}
	private long firstBackTime;

	@Override
	public void onBackPressed() {

		if (System.currentTimeMillis() - firstBackTime > 2000) {
			Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
			firstBackTime = System.currentTimeMillis();
			return;
		}

		super.onBackPressed();
		
	
    }
}
