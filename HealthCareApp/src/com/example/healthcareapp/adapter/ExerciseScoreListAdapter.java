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
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_score_list, null);
			holder.mTitleText = (TextView) convertView.findViewById(R.id.score_adapter_exercise_name);
			holder.mScoreText = (TextView) convertView.findViewById(R.id.score_adapter_meter_value_text);
			holder.mScoreMeter = (RadialProgressView) convertView.findViewById(R.id.score_adapter_meter);
			convertView.setTag(holder);
		} else 
			holder = (ViewHolder) convertView.getTag();
		
		holder.mScoreMeter.setMaxValue(100);
		holder.mTitleText.setText(mData.get(position).getName());
		holder.mScoreText.setText(String.valueOf(mData.get(position).getScore()));
		holder.mScoreMeter.setCurrentValue(mData.get(position).getScore());
		return convertView;
	}

	static class ViewHolder {
		TextView mTitleText, mScoreText;
		RadialProgressView mScoreMeter;
	}
}
