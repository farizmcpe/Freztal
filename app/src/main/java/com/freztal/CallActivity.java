package com.freztal;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.telecom.Call;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.os.Build;
import android.Manifest;
import android.util.Log;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {

	EditText number;
	Button callperm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		Toast.makeText(getApplication(), "Call Anyone!", Toast.LENGTH_SHORT).show();
		if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){

                Log.d("permission","permission denied to CALL_PHONE - requesting it");
                String[] permissions = {Manifest.permission.CALL_PHONE};
                requestPermissions(permissions,1);
            }
        } 
		
		number = findViewById(R.id.number);
	}
	public void call(View v) {
		String st=number.getText().toString();
		
		Intent in= new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + st));
		startActivity(in);
		Toast.makeText(getApplication(), "Calling!", Toast.LENGTH_SHORT).show();
	
	}





}
