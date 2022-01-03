package com.freztal;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.os.Build;
import android.Manifest;
import android.util.Log;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


public class GrantActivity extends AppCompatActivity {

	Button gst;
	Button gotmc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grantp);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

				Intent r = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(r);
				finish();
				}
				}
				
		
	gst = findViewById(R.id.gst);
	gst.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

				Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
				String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
				requestPermissions(permissions, 1);
				
			}
			}
		
				
				
		}

    });
	
		gotmc = findViewById(R.id.gotmc);
		gotmc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
	
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

				Intent tmc = new Intent(getApplicationContext(), ToolMcFRZ.class);
				startActivity(tmc);
				finish();
				} else {
					Toast.makeText(getApplication(), "Permission Denied!", Toast.LENGTH_SHORT).show();
			}
		}
		}
		});
	
	}
    
}
