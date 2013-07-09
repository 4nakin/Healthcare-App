package com.example.healthcareapp.views;

import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class LoginView {
	
	private ViewGroup mViewLoginHolder, mViewLoginFooterHolder;
	@SuppressWarnings("unused")
	private Context mContext;

	public LoginView(final Context context, final LinearLayout container) {
		mContext = context;
		mViewLoginHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_login, container, false);
		mViewLoginFooterHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_login_footer, container, false);
		
		mViewLoginHolder.findViewById(R.id.login_procced_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				context.startActivity(new Intent(context, MainActivity.class));
			}
		});
		
		mViewLoginFooterHolder.findViewById(R.id.login_new_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				container.removeView(mViewLoginFooterHolder);
				container.removeView(mViewLoginHolder);				
				new SignUpView(context, container);
			}
		});
		
		mViewLoginHolder.findViewById(R.id.login_forgot_password).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewLoginFooterHolder);
				container.removeView(mViewLoginHolder);		
				new ForgotPasswordView(context, container);
			}
		});
		
		container.addView(mViewLoginHolder);
		container.addView(mViewLoginFooterHolder);
	}
}
