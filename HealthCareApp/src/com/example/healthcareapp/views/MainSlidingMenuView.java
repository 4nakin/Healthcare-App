package com.example.healthcareapp.views;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.adapter.MainMenuListAdapter;
import com.example.healthcareapp.interfaces.OnMainSlidingMenuItemSelected;
import com.example.healthcareapp.model.MainMenuListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainSlidingMenuView {
	
	private TextView mUsername;
	private View mViewHolder;
	private ListView mMainListView;
	private MainMenuListAdapter mAdapter;
	private ArrayList<MainMenuListItem> mData = new ArrayList<MainMenuListItem>();
	private OnMainSlidingMenuItemSelected mCallback;

	public MainSlidingMenuView(Context context) {
		mAdapter = new MainMenuListAdapter(context);
		mData.add(new MainMenuListItem(R.drawable.ic_action_courses, context.getString(R.string.my_courses)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_statistics, context.getString(R.string.statistics)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_settings, context.getString(R.string.settings)));
		mData.add(new MainMenuListItem(R.drawable.ic_action_about, context.getString(R.string.about)));
		mAdapter.setListData(mData);
		
		mViewHolder = LayoutInflater.from(context).inflate(R.layout.view_sliding_menu_main, null);
		mMainListView = (ListView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_list);
		mUsername = (TextView) mViewHolder.findViewById(R.id.sliding_menu_main_menu_user);
		
		mUsername.setText("Arindam Nath");
		mMainListView.setAdapter(mAdapter);
		mMainListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mMainListView.setItemChecked(0, true);
		
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
}
