package com.freztal;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Switch;
import java.io.File;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
	Switch lgt;
	Button cc;
	Button cd;
	Button ct;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		
		lgt = findViewById(R.id.light);
		lgt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
				    setContentView(R.layout.temp);
					}
					});
					
	    cc = findViewById(R.id.ccache);
		cc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(getApplication(), "Successfully cleared cache", Toast.LENGTH_SHORT).show();
					try {
						File dir = getCacheDir();
						deleteDir(dir);
					} catch (Exception e) {}
				}

				public boolean deleteDir(File dir) {
					if (dir != null && dir.isDirectory()) {
						String[] children = dir.list();
						for (int i = 0; i < children.length; i++) {
							boolean success = deleteDir(new File(dir, children[i]));
							if (!success) {
								return false;
							}
						}
						return dir.delete();

					}
					else if(dir!= null && dir.isFile())
						return dir.delete();
					else {
						return false;
					}

				
				}
			});

		cd = findViewById(R.id.cdata);
		cd.setOnClickListener(new View.OnClickListener() {
		@Override
				public void onClick(View view) {
				Toast.makeText(getApplication(), "Successfully cleared data", Toast.LENGTH_SHORT).show();
					try {
					File dir = getDataDir();
						deleteDir(dir);
						} catch (Exception e) {}
					}

				public boolean deleteDir(File dir) {
				if (dir != null && dir.isDirectory()) {
					String[] children = dir.list();
						for (int i = 0; i < children.length; i++) {
						boolean success = deleteDir(new File(dir, children[i]));
							if (!success) {
							return false;
								}
							}
						return dir.delete();

						}
					else if(dir!= null && dir.isFile())
					return dir.delete();
						else {
					return false;
						}


					}
				});
				
		ct = findViewById(R.id.ctemp);
		ct.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					try {
						
					File tempFile = new File(Environment.getExternalStorageDirectory(), "/storage/emulated/0/Freztal/Downloads");

					
						
						deleteDir(tempFile);
					} catch (Exception e) {}
				}

				public boolean deleteDir(File dir) {
					if (dir != null && dir.isDirectory()) {
						String[] children = dir.list();
						for (int i = 0; i < children.length; i++) {
							boolean success = deleteDir(new File(dir, children[i]));
							if (!success) {
								return false;
							}
						}
						return dir.delete();

					}
					else if(dir!= null && dir.isFile())
						return dir.delete();
					else {
						return false;
					}


				}
						
					

			});
		
     }
	 }
