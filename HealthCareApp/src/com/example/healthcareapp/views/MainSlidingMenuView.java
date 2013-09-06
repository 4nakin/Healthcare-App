package com.example.healthcareapp.views;

import java.util.ArrayList;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.adapter.MainMenuListAdapter;
import com.example.healthcareapp.interfaces.OnMainSlidingMenuItemSelected;
import com.example.healthcareapp.model.MainMenuListItem;
import com.example.healthcareapp.util.AppPreferences;
import com.example.healthcareapp.widgets.RadialProgressView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainSlidingMenuView {
	
	private TextView mAge, mUsername, mGender;
	private RadialProgressView mProgressView;
	private View mViewHolder;
	private ListView mMainListView;
	private MainMenuListAdapter mAdapter;
	private ArrayList<MainMenuListItem> mData = new ArrayList<MainMenuListItem>();
	private OnMainSlidingMenuItemSelected mCallback;
	private AppPreferences mPrefs;

	public MainSlidingMenuView(Context context) {
		mAdapter = new MainMenuListAdapter(context);
		mPrefs = new AppPreferences(context);
		mData.add(new MainMenuListItem(R.drawable.ic_action_courses, context.getString(R.string.my_courses)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_statistics, context.getString(R.string.statistics)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_settings, context.getString(R.string.settings)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_about, context.getString(R.string.about)));
		mAdapter.setListData(mData);
		
		mViewHolder = LayoutInflater.from(context).inflate(R.layout.view_sliding_menu_main, null);
		mMainListView = (ListView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_list);
		mAge = (TextView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_user_age);
		mUsername = (TextView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_user);
		mGender = (TextView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_user_gender);
		mProgressView = (RadialProgressView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_progress_view);
		mProgressView.setMaxValue(101);	
		initView(context);
		
		mViewHolder.findViewById(R.id.sliding_menu_main_menu_edit_profile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mCallback.OnMenuItemSelected(OnMainSlidingMenuItemSelected.EDIT_PROFILE);
				mMainListView.setItemChecked(ListView.INVALID_POSITION, true);
			}
		});
		
		mMainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(mCallback != null)
					switch(arg2) {
					case 0:
						mCallback.OnMenuItemSelected(OnMainSlidingMenuItemSelected.COURSES);
						break;
					case 1:
						mCallback.OnMenuItemSelected(OnMainSlidingMenuItemSelected.STATISTICS);
						break;
					case 2:
						mCallback.OnMenuItemSelected(OnMainSlidingMenuItemSelected.SETTINGS);
						break;
					case 3:
						mCallback.OnMenuItemSelected(OnMainSlidingMenuItemSelected.ABOUT);
						break;
					}
			}
		});
	}
	
	public View getView() {
		return this.mViewHolder;
	}

	/**
	 * @param mCallback the mCallback to set
	 */
	public void setOnMainSlidingMenuItemSelected(OnMainSlidingMenuItemSelected mCallback) {
		this.mCallback = mCallback;
	}
	
	public void initView(Context context) {
		updateUserInfo(context);
		mMainListView.setAdapter(mAdapter);
		mMainListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mMainListView.setItemChecked(0, true);
	}
	
	public void setListSelection(int selection) {
		switch (selection) {
		case OnMainSlidingMenuItemSelected.COURSES:
			mMainListView.setItemChecked(0, true);
			break;
		case OnMainSlidingMenuItemSelected.STATISTICS:
			mMainListView.setItemChecked(1, true);
			break;
		case OnMainSlidingMenuItemSelected.SETTINGS:
			mMainListView.setItemChecked(2, true);
			break;
		case OnMainSlidingMenuItemSelected.ABOUT:
			mMainListView.setItemChecked(3, true);
			break;
		case OnMainSlidingMenuItemSelected.EDIT_PROFILE:
			mMainListView.setItemChecked(ListView.INVALID_POSITION, true);
			break;
		}
	}
	
	public int getAge(int year, int month, int day) {
		DateMidnight birthdate = new DateMidnight(year, month+1, day);
		DateTime now = new DateTime();
		Years age = Years.yearsBetween(birthdate, now);
		return age.getYears();
	}
	
	public void updateUserInfo(Context context) {
		if(mPrefs.getFirstname() != null && mPrefs.getLastname() != null)
			mUsername.setText(mPrefs.getFirstname() + " " + mPrefs.getLastname());
		if(mPrefs.getDOBYear() != -1 && mPrefs.getDOBMonth() != -1 && mPrefs.getDOBDay() != -1) {
			mAge.setText(String.valueOf(getAge(mPrefs.getDOBYear(), mPrefs.getDOBMonth(), mPrefs.getDOBDay())));
			mProgressView.setCurrentValue(getAge(mPrefs.getDOBYear(), mPrefs.getDOBMonth(), mPrefs.getDOBDay()));
		}
		if(mPrefs.getGender() != -1) 
			mGender.setText((mPrefs.getGender() == 0) ? context.getString(R.string.male) : context.getString(R.string.female));
	}
}
