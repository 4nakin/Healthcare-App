package com.example.healthcareapp.fragments;

import com.example.healthcareapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChangePasswordFragment extends Fragment {

	private View mViewHolder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mViewHolder = inflater.inflate(R.layout.fragment_change_password, null);
		return mViewHolder;
	}
}
