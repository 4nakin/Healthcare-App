package com.example.healthcareapp.fragments;

import com.healthcareapp.IOIyears.R;

import android.app.Fragment;
import android.os.Bundle;
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
