package com.example.healthcareapp.fragments;

import com.example.healthcareapp.R;
import com.example.healthcareapp.UpdateProfileActivity;
import com.example.healthcareapp.widgets.RadialProgressView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	
	private View mViewHolder;
	private TextView mAge, mName, mGender;
	private RadialProgressView mProgressView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mViewHolder = inflater.inflate(R.layout.fragment_profile, null);
		mAge = (TextView) mViewHolder.findViewById(R.id.profile_user_age);
		mName = (TextView) mViewHolder.findViewById(R.id.profile_name);
		mGender = (TextView) mViewHolder.findViewById(R.id.profile_gender);
		mProgressView = (RadialProgressView) mViewHolder.findViewById(R.id.profile_age_progress_view);
		
		mAge.setText("23");
		mName.setText("Arindam Nath");
		mGender.setText("Male");
		mProgressView.setMaxValue(101);
		mProgressView.setCurrentValue(23);
		
		mViewHolder.findViewById(R.id.profile_edit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(), UpdateProfileActivity.class));
			}
		});
		
		mViewHolder.findViewById(R.id.profile_change_password).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		
		mViewHolder.findViewById(R.id.profile_logout).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		
		mViewHolder.findViewById(R.id.profile_questionnaire).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		return mViewHolder;
	}
}
