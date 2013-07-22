package com.example.healthcareapp.views;

import com.example.healthcareapp.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ForgotPasswordView {
	
	private ViewGroup mViewForgotPasswordHolder;

	public ForgotPasswordView(final Activity activity, final LinearLayout container) {
		mViewForgotPasswordHolder = (ViewGroup) LayoutInflater.from(
				activity).inflate(R.layout.layout_forgot_password, container, false);
		
		mViewForgotPasswordHolder.findViewById(R.id.sign_up_exsisting_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewForgotPasswordHolder);
				new LoginView(activity, container);
			}
		});
		
		container.addView(mViewForgotPasswordHolder);
	}
}
