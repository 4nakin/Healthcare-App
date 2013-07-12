package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.ExerciseScoreListItem;
import com.example.healthcareapp.widgets.RadialProgressView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciseScoreListAdapter extends BaseAdapter{
	
	private ArrayList<ExerciseScoreListItem> mData = new ArrayList<ExerciseScoreListItem>();
	private LayoutInflater mInflater;
	
	public ExerciseScoreListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<ExerciseScoreListItem> data) {
		mData = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public ExerciseScoreListItem getItem(int pos) {
		return mData.get(pos);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_score_list, null);
			TextView mTitleText = (TextView) convertView.findViewById(R.id.score_adapter_exercise_name);
			TextView mScoreText = (TextView) convertView.findViewById(R.id.score_adapter_meter_value_text);
			RadialProgressView mScoreMeter = (RadialProgressView) convertView.findViewById(R.id.score_adapter_meter);
			mScoreMeter.setMaxValue(100);
			
			mTitleText.setText(mData.get(position).getName());
			mScoreText.setText(String.valueOf(mData.get(position).getScore()));
			mScoreMeter.setCurrentValue(mData.get(position).getScore());
		}
		return convertView;
	}

}
