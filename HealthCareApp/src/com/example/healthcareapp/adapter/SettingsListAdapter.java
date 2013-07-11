package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.SettingsListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsListAdapter extends BaseAdapter {
	
	private ArrayList<SettingsListItem> mData = new ArrayList<SettingsListItem>();
	private LayoutInflater mInflater;
	
	public SettingsListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<SettingsListItem> data) {
		mData = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public SettingsListItem getItem(int pos) {
		return mData.get(pos);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_settings_list, null);
			View mColorView = (View) convertView.findViewById(R.id.adapter_settings_list_color);
			TextView mTitleText = (TextView) convertView.findViewById(R.id.adapter_settings_list_title_text);
			TextView mDescriptionText = (TextView) convertView.findViewById(R.id.adapter_settings_list_description_text);
			ImageView mIcon = (ImageView) convertView.findViewById(R.id.adapter_settings_list_icon);
			mTitleText.setText(mData.get(position).getTitle());
			mDescriptionText.setText(mData.get(position).getDescription());
			mIcon.setImageResource(mData.get(position).getImageID());
			if((position+1) % 2 == 0)
				mColorView.setBackgroundColor(mInflater.getContext().getResources().getColor(android.R.color.holo_purple));
			else if((position+1) % 3 == 0)
				mColorView.setBackgroundColor(mInflater.getContext().getResources().getColor(android.R.color.holo_green_dark));
			else if((position+1) % 5 == 0)
				mColorView.setBackgroundColor(mInflater.getContext().getResources().getColor(android.R.color.holo_blue_dark));
			else
				mColorView.setBackgroundColor(mInflater.getContext().getResources().getColor(android.R.color.holo_orange_dark));
		}		
		return convertView;
	}

}
