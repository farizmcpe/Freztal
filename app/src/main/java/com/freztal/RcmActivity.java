package com.freztal;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.ValueCallback;
import android.net.Uri;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;
import android.webkit.WebViewClient;
import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebSettings;
import android.widget.Toast;
import android.webkit.WebChromeClient;
import android.util.Log;
import android.Manifest;
import android.webkit.DownloadListener;
import android.app.DownloadManager;
import android.webkit.URLUtil;
import android.content.Intent;
import android.content.ClipData;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.provider.MediaStore;
import android.content.res.Configuration;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.content.pm.PackageManager;
import java.util.Date;
import android.webkit.CookieManager;
import android.widget.Button;
import android.view.View;
import android.app.Activity;
import android.content.Context;

public class RcmActivity extends AppCompatActivity {
    private String cam_file_data = null;
    private ValueCallback<Uri> file_data;
    private ValueCallback<Uri[]> file_path;
    private final static int file_req_code = 1;

	Button shaderlm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rcm);
		Toast.makeText(getApplication(), "Download Resource Packs!", Toast.LENGTH_SHORT).show();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

				Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
				String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
				requestPermissions(permissions, 1);


			}
		}
		shaderlm = findViewById(R.id.shaderlm);
		shaderlm.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
							Toast.makeText(getApplication(), "Permission Denied!", Toast.LENGTH_SHORT).show();
				} else {

					DownloadManager	downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);


					Uri uri = Uri.parse("https://download1510.mediafire.com/4ojx5uzra7mg/ipnpnh3vel3fxy9/BicubicSha.mcpack");

					DownloadManager.Request request = new DownloadManager.Request(uri);
					request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					request.setDescription("Downloading file....");
					request.setTitle("Bibuc shader");
					request.allowScanningByMediaScanner();
					request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Bibuc Shader Beta.mcpack");
					Long reference = downloadManager.enqueue(request);
					Toast.makeText(getApplication(), "Downloading File", Toast.LENGTH_SHORT).show();
				}
				}
				}
			});
	}

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(Build.VERSION.SDK_INT >= 21){
            Uri[] results = null;

            /*-- if file request cancelled; exited camera. we need to send null value to make future attempts workable --*/
            if (resultCode == Activity.RESULT_CANCELED) {
                file_path.onReceiveValue(null);
                return;
            }

            /*-- continue if response is positive --*/
            if(resultCode== Activity.RESULT_OK){
                if(null == file_path){
                    return;
                }
                ClipData clipData;
                String stringData;

                try {
                    clipData = intent.getClipData();
                    stringData = intent.getDataString();
                }catch (Exception e){
                    clipData = null;
                    stringData = null;
                }
                if (clipData == null && stringData == null && cam_file_data != null) {
                    results = new Uri[]{Uri.parse(cam_file_data)};
                }else{
                    if (clipData != null) { 
                        final int numSelectedFiles = clipData.getItemCount();
                        results = new Uri[numSelectedFiles];
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            results[i] = clipData.getItemAt(i).getUri();
                        }
                    } else {
                        try {
                            Bitmap cam_photo = (Bitmap) intent.getExtras().get("data");
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            cam_photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                            stringData = MediaStore.Images.Media.insertImage(this.getContentResolver(), cam_photo, null, null);
                        }catch (Exception ignored){}

                        results = new Uri[]{Uri.parse(stringData)};
                    }
                }
            }

            file_path.onReceiveValue(results);
            file_path = null;
        }else{
            if(requestCode == file_req_code){
                if(null == file_data) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                file_data.onReceiveValue(result);
                file_data = null;
            }
        }
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}


    private File create_image() throws IOException{
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",storageDir);
    }

    private File create_video() throws IOException {
        @SuppressLint("SimpleDateFormat")
			String file_name    = new SimpleDateFormat("yyyy_mm_ss").format(new Date());
        String new_name     = "file_"+file_name+"_";
        File sd_directory   = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(new_name, ".3gp", sd_directory);


	}




}
