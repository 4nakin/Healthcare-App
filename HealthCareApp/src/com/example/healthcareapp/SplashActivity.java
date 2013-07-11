package com.example.healthcareapp;

import com.example.healthcareapp.util.AppPreferences;
import com.example.healthcareapp.views.LoginView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	
	private LinearLayout mContainer;
	private AppPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mPrefs = new AppPreferences(this);
		mContainer = (LinearLayout) findViewById(R.id.splash_container);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				if(mPrefs.isUserLoggedIn()) {
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
					finish();
				} else 
					new LoginView(SplashActivity.this, mContainer);
            }
		}, 2500);
	}
}
