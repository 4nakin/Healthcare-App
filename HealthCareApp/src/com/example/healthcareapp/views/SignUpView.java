package com.example.healthcareapp.views;

import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.R;
import com.example.healthcareapp.threads.RegisterUserTask;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SignUpView implements RegisterUserTask.OnRegisterProcessCompletedListener {
	
	private ViewGroup mViewSignUpHolder;
	private EditText mUsername, mFirstname, mLastname, mEmail, mPassword, mRePassword;
	private Activity mActivity;

	public SignUpView(final Activity activity, final LinearLayout container) {
		mActivity = activity;
		mViewSignUpHolder = (ViewGroup) LayoutInflater.from(
				activity).inflate(R.layout.layout_signup, container, false);
		
		mUsername = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_username);
		mFirstname = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_first_name);
		mLastname = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_last_name);
		mEmail = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_email);
		mPassword = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_password);
		mRePassword = (EditText) mViewSignUpHolder.findViewById(R.id.sign_up_re_enter_password);
		
		mViewSignUpHolder.findViewById(R.id.sign_up_procced_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new RegisterUserTask(activity, SignUpView.this)
					.execute(new EditText[] {mFirstname, mLastname, mUsername, mEmail, mPassword, mRePassword});
			}
		});
		
		mViewSignUpHolder.findViewById(R.id.sign_up_exsisting_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewSignUpHolder);
				new LoginView(activity, container);
			}
		});
		
		container.addView(mViewSignUpHolder);
	}
	
	@Override
	public void onRegisterSuccessful() {
		mActivity.startActivity(new Intent(mActivity, MainActivity.class));
		mActivity.finish();
	}
}
