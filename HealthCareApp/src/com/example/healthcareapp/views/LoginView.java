package com.example.healthcareapp.views;

import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.R;
import com.example.healthcareapp.threads.AuthenticateUserTask;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LoginView implements AuthenticateUserTask.OnAuthenticateUserListener {
	
	private ViewGroup mViewLoginHolder;
	private EditText mUsername, mPassword;
	private Activity mActivity;

	public LoginView(final Activity activity, final LinearLayout container) {
		mActivity = activity;
		mViewLoginHolder = (ViewGroup) LayoutInflater.from(
				activity).inflate(R.layout.layout_login, container, false);
		
		mUsername = (EditText) mViewLoginHolder.findViewById(R.id.login_username);
		mPassword = (EditText) mViewLoginHolder.findViewById(R.id.login_password);
		
		mViewLoginHolder.findViewById(R.id.login_procced_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AuthenticateUserTask(activity, LoginView.this)
					.execute(new EditText[] {mUsername, mPassword});
			}
		});
		
		mViewLoginHolder.findViewById(R.id.login_new_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				container.removeView(mViewLoginHolder);				
				new SignUpView(activity, container);
			}
		});
		
		mViewLoginHolder.findViewById(R.id.login_forgot_password).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewLoginHolder);		
				new ForgotPasswordView(activity, container);
			}
		});
		
		container.addView(mViewLoginHolder);
	}

	@Override
	public void onAuthenticationSuccess() {
		//TODO Remember me option
		mActivity.startActivity(new Intent(mActivity, MainActivity.class));
		mActivity.finish();
	}
}
