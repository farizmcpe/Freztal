package com.freztal;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class OpenApps extends AppCompatActivity {
	
Button toolbox;
Button ldc;
Button lyt;
Button lmc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		Toast.makeText(getApplication(), "Application Launcher!", Toast.LENGTH_SHORT).show();
		toolbox = findViewById(R.id.toolbox);
		toolbox.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view) { 
					Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("io.mrarm.mctoolbox");
					if (launchIntentForPackage != null) {
						startActivity(launchIntentForPackage);

					} else {
						Toast.makeText(getApplication(), "APP NOT INSTALLED ", 0).show();
					}
	
				
				}

			});
		ldc = findViewById(R.id.ldiscord);
		ldc.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view) { 
					Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.discord");
					if (launchIntentForPackage != null) {
						startActivity(launchIntentForPackage);

					} else {
						Toast.makeText(getApplication(), "APP NOT INSTALLED ", 0).show();
					}


				}

			});
	    lyt = findViewById(R.id.lyoutube);
		lyt.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view) { 
					Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
					if (launchIntentForPackage != null) {
						startActivity(launchIntentForPackage);

					} else {
						Toast.makeText(getApplication(), "APP NOT INSTALLED ", 0).show();
					}


				}

			});
		lmc = findViewById(R.id.lmc);
		lmc.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view) { 
					Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
					Toast.makeText(getApplication(), "Launched!", Toast.LENGTH_SHORT).show();
					if (launchIntentForPackage != null) {
						startActivity(launchIntentForPackage);

					} else {
						Toast.makeText(getApplication(), "APP NOT INSTALLED ", 0).show();
					}


				}

			});
	}

	
    
    
    
}
