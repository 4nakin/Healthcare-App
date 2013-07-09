package com.example.healthcareapp.views;

import com.example.healthcareapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SignUpView {
	
	private ViewGroup mViewSignUpHolder, mViewSignUpFooterHolder;
	@SuppressWarnings("unused")
	private Context mContext;

	public SignUpView(final Context context, final LinearLayout container) {
		mContext = context;
		mViewSignUpHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_signup, container, false);
		mViewSignUpFooterHolder = (ViewGroup) LayoutInflater.from(
				context).inflate(R.layout.layout_signup_footer, container, false);
		
		mViewSignUpFooterHolder.findViewById(R.id.sign_up_exsisting_user).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeView(mViewSignUpFooterHolder);
				container.removeView(mViewSignUpHolder);
				new LoginView(context, container);
			}
		});
		
		container.addView(mViewSignUpHolder);
		container.addView(mViewSignUpFooterHolder);
	}
}
