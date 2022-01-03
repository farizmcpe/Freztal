package com.freztal;
import android.widget.Toast;
import java.io.File;
import android.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class splash extends AppCompatActivity
{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
					Intent i=new Intent(splash.this, MainActivity.class);
					startActivity(i);
					finish();
	}


}
