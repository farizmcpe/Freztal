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
import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.Cursor;
import android.content.IntentFilter;

public class ToolMcFRZ extends AppCompatActivity {
    private String cam_file_data = null;
    private ValueCallback<Uri> file_data;
    private ValueCallback<Uri[]> file_path;
    private final static int file_req_code = 1;
	String url;
	String userAgent;
	String contentDisposition; 
	String mimeType;
	long contentLength;
	

	Button tmc;
	Button otmc;
	Button dtmc;
	Button itmc;
	public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo.isConnected();
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String fc = "Freztal/Dowbloads";

		File f = new File(Environment.getExternalStorageDirectory()+ fc);
		if (!f.exists()) {
			f.mkdirs();
		}
		setContentView(R.layout.activity_tmc);
		Toast.makeText(getApplication(), "Download ToolMcFRZ!", Toast.LENGTH_SHORT).show();
		tmc = findViewById(R.id.tmc);
		tmc.setOnClickListener(new View.OnClickListener() {

				private long downloadID;

		
				@Override
				public void onClick(View view) {

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

							Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
							String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
							requestPermissions(permissions, 1);
						}
					}
					
				
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
							Toast.makeText(getApplication(), "Permission Denied!", Toast.LENGTH_SHORT).show();
						} else {
					
							

					DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
					Uri uri = Uri.parse("https://www.mediafire.com/file/3i504rrknje40fu/ToolMcFRZ_V7.7_INFINITE_PREMIUM_32-bit.apk/file");
					DownloadManager.Request request = new DownloadManager.Request(uri);
					
				    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
					request.setAllowedOverRoaming(false);
					request.setTitle("ToolMcFRZ");
				    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					request.allowScanningByMediaScanner();
					request.setDescription("Downloading File...");
					request.setDestinationInExternalPublicDir("Freztal/Downloads",("ToolMcFRZ 7.7") + ".apk");

				    downloadID = downloadManager.enqueue(request);
					
					Toast.makeText(getApplication(), "Downloading File", Toast.LENGTH_SHORT).show();
				

							BroadcastReceiver receiver = new BroadcastReceiver() {

								private DownloadManager dm;
								@Override
								public void onReceive(Context context, Intent intent) {
									String action = intent.getAction();
									if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
										long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
										DownloadManager.Query query = new DownloadManager.Query();
										query.setFilterById(downloadID);
										dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
										Cursor c = dm.query(query);
										if (c.moveToFirst()) {
											int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
											if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
												String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
												//TODO : Use this local uri and launch intent to open file

											}
										}
									}
								}
							};
							registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
					
							
					   
				}
				}
				}
				
			});
	
	 
	
	otmc = findViewById(R.id.otmc);
	otmc.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		String fd =  Environment.getExternalStorageDirectory().getPath()+ "/" + "/storage/emulated/0/Freztal/Downloads" + "/";

			Intent fda = new Intent(Intent.ACTION_VIEW);
		Uri u = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/" + "/storage/emulated/0/Freztal/Downloads" + "/");
		fda.setDataAndType(u, "*/*");
		
			startActivity(Intent.createChooser(fda, "Open Folder"));
		


	}
	
	});
	
		itmc = findViewById(R.id.itmc);
		itmc.setOnClickListener(new View.OnClickListener() {
				private Context context;
				@Override
				public void onClick(View view) {
					File apkFile = new File("/storage/emulated/0/Download/ToolMcFRZ 7.7.apk");
					
					if (apkFile.exists()) {
					Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
					intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
					intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(intent);
					
					} else {
						Toast.makeText(getApplication(), "Apk does not exists!", Toast.LENGTH_SHORT).show();
						}
						
					}

			});
	
	dtmc = findViewById(R.id.dtmc);
	dtmc.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		File file = new File(Environment.getExternalStorageDirectory(), "Download/ToolMcFRZ 7.7.apk");
		if (file.exists()) {
		file.delete();
		Toast.makeText(getApplication(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
	          } else {
				  Toast.makeText(getApplication(), "Apk does not exists!", Toast.LENGTH_SHORT).show();
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
            if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                file_path.onReceiveValue(null);
                return;
            }

            /*-- continue if response is positive --*/
            if(resultCode== AppCompatActivity.RESULT_OK){
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
