package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.ExerciseItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciseListAdapter extends BaseAdapter {
	
	private ArrayList<ExerciseItem> mData = new ArrayList<ExerciseItem>();
	private LayoutInflater mInflater;
	
	public ExerciseListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<ExerciseItem> data) {
		mData = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public ExerciseItem getItem(int pos) {
		return mData.get(pos);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_main_list, null);
			TextView mTitleText = (TextView) convertView.findViewById(R.id.exercise_title);
			TextView mDescriptionText = (TextView) convertView.findViewById(R.id.exercise_description);
			TextView mCountText = (TextView) convertView.findViewById(R.id.exercise_count);
			mTitleText.setText(mData.get(position).getExerciseName());
			mDescriptionText.setText(mData.get(position).getmExerciseDescription());
			mCountText.setText(String.valueOf(position + 1));
		}
		return convertView;
	}
}
