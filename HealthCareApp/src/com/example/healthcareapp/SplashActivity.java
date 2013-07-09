package com.example.healthcareapp;

import com.example.healthcareapp.views.LoginView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	
	private LinearLayout mContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mContainer = (LinearLayout) findViewById(R.id.splash_container);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				new LoginView(SplashActivity.this, mContainer);
            }
		}, 2500);
	}
}
