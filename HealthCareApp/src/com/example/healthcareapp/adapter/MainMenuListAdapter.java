package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.MainMenuListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainMenuListAdapter extends BaseAdapter {

	private ArrayList<MainMenuListItem> mData = new ArrayList<MainMenuListItem>();
	private LayoutInflater mInflater;
	
	public MainMenuListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<MainMenuListItem> data) {
		mData = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_menu_list, null);
			holder.mTitleText = (TextView) convertView.findViewById(R.id.adapter_main_menu_list_name);
			convertView.setTag(holder);
		} else 
			holder = (ViewHolder) convertView.getTag();
		
		holder.mTitleText.setText(mData.get(position).getName());
		holder.mTitleText.setCompoundDrawablesWithIntrinsicBounds(mData.get(position).getImageID(), 0, 0, 0);
		return convertView;
	}

	static class ViewHolder {
		TextView mTitleText;
	}
}
