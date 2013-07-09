package com.example.healthcareapp.views;

import com.example.healthcareapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ForgotPasswordView {
	
	private ViewGroup mViewForgotPasswordHolder, mViewForgotPasswordFooterHolder;

	public ForgotPasswordView(final Context context, final LinearLayout container) {
		mViewForgotPasswordHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_forgot_password, container, false);
		mViewForgotPasswordFooterHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_signup_footer, container, false);
		
		mViewForgotPasswordFooterHolder.findViewById(R.id.sign_up_exsisting_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewForgotPasswordFooterHolder);
				container.removeView(mViewForgotPasswordHolder);
				new LoginView(context, container);
			}
		});
		
		container.addView(mViewForgotPasswordHolder);
		container.addView(mViewForgotPasswordFooterHolder);
	}
}
