package com.example.healthcareapp.fragments;

import java.util.Calendar;

import com.example.healthcareapp.QuestionnaireMainActivity;
import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.util.AppPreferences;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment implements  OnDateSetListener {
	
	private DatePickerDialog mDatePickerDialog;
	private View mViewHolder;
	private AppPreferences mPrefs;
	private EditText mFirstname, mLastname, mEmail;
	private RadioButton mMaleBtn, mFemaleBtn;
	private final Calendar mCalendarCurrent = Calendar.getInstance();
	private final Calendar mCalendarSelected = Calendar.getInstance();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mPrefs = new AppPreferences(getActivity());
		mViewHolder = inflater.inflate(R.layout.fragment_profile, null);
		mFirstname = (EditText) mViewHolder.findViewById(R.id.profile_first_name_edit);
		mLastname = (EditText) mViewHolder.findViewById(R.id.profile_last_name_edit);
		mEmail = (EditText) mViewHolder.findViewById(R.id.profile_email_edit);
		mMaleBtn = (RadioButton) mViewHolder.findViewById(R.id.profile_gender_male_btn);
		mFemaleBtn = (RadioButton) mViewHolder.findViewById(R.id.profile_gender_female_btn);
		
		initView();
		
		mViewHolder.findViewById(R.id.profile_change_dob).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDatePickerDialog.show();
			}
		});
		
		mViewHolder.findViewById(R.id.profile_save_profile_changes).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveData();
			}
		});
		
		mViewHolder.findViewById(R.id.profile_questionnaire).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), QuestionnaireMainActivity.class));
			}
		});
		
		mViewHolder.findViewById(R.id.profile_upgrade_profile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), R.string.feature_na, Toast.LENGTH_LONG).show();
			}
		});
		
		return mViewHolder;
	}
	
	private void initView() {
		if(mPrefs.getFirstname() != null)
			mFirstname.setText(mPrefs.getFirstname());
		if(mPrefs.getLastname() != null)
			mLastname.setText(mPrefs.getLastname());
		if(mPrefs.getEmail() != null) 
			mEmail.setText(mPrefs.getEmail());
		if(mPrefs.getGender() == 0)
			mMaleBtn.setChecked(true);
		else if(mPrefs.getGender() == 1)
			mFemaleBtn.setChecked(true);
		
		if(mPrefs.getDOBYear() != -1 && mPrefs.getDOBMonth() != -1 && mPrefs.getDOBDay() != -1) {
			//Set the preset value
			mCalendarSelected.set(mPrefs.getDOBYear(), mPrefs.getDOBMonth(), mPrefs.getDOBDay());
			//Display the preset value
			((TextView) mViewHolder.findViewById(R.id.profile_change_dob))
				.setText(getString(R.string.born_on) + " " +
						getResources().getStringArray(R.array.months)[mPrefs.getDOBMonth()] + " " 
						+ String.valueOf(mPrefs.getDOBDay()) + ", " + String.valueOf(mPrefs.getDOBYear()));
			//Load the date picker with the preset value
			mDatePickerDialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, 
					this, mCalendarSelected.get(Calendar.YEAR), mCalendarSelected.get(Calendar.MONTH), 
					mCalendarSelected.get(Calendar.DAY_OF_MONTH));
		} else 
			mDatePickerDialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, 
					this, mCalendarCurrent.get(Calendar.YEAR), mCalendarCurrent.get(Calendar.MONTH), 
					mCalendarCurrent.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * TODO Temp...to be moved to async task with online upload
	 */
	private void saveData() {
		if(mFirstname.getText().toString().trim().length() > 0 &&
				mLastname.getText().toString().trim().length() > 0 &&
				mEmail.getText().toString().trim().length() > 0) {
			mPrefs.setFirstname(mFirstname.getText().toString().trim());
			mPrefs.setLastname(mLastname.getText().toString().trim());
			mPrefs.setEmail(mEmail.getText().toString().trim());
			if(mMaleBtn.isChecked())
				mPrefs.setGender(0);
			else if(mFemaleBtn.isChecked())
				mPrefs.setGender(1);
			if(!mCalendarSelected.after(mCalendarCurrent)) 
				mPrefs.setDateOfBirth(mCalendarSelected.get(Calendar.YEAR), 
						mCalendarSelected.get(Calendar.MONTH), 
						mCalendarSelected.get(Calendar.DAY_OF_MONTH));
			Toast.makeText(getActivity(), R.string.msg_changes_saved, Toast.LENGTH_LONG).show();
		} else 
			Toast.makeText(getActivity(), R.string.msg_registration_empty_field, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		mCalendarSelected.set(year, monthOfYear, dayOfMonth);
    	if(!mCalendarSelected.after(mCalendarCurrent)) 
			((TextView) mViewHolder.findViewById(R.id.profile_change_dob))
				.setText(getString(R.string.born_on) + " " +
						getResources().getStringArray(R.array.months)[monthOfYear] + " " 
						+ String.valueOf(dayOfMonth) + ", " + String.valueOf(year));
	}
	
}
