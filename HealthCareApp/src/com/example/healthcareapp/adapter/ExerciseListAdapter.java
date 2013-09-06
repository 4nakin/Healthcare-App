package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
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
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_main_list, null);
			holder.mTitleText = (TextView) convertView.findViewById(R.id.exercise_title);
			holder.mDescriptionText = (TextView) convertView.findViewById(R.id.exercise_description);
			holder.mCountText = (TextView) convertView.findViewById(R.id.exercise_count);
			convertView.setTag(holder);
		} else 
			holder  = (ViewHolder) convertView.getTag();
		
		holder.mTitleText.setText(mData.get(position).getExerciseName());
		holder.mDescriptionText.setText(mData.get(position).getExerciseDescription());
		holder.mCountText.setText(String.valueOf(position + 1));
		return convertView;
	}
	
	static class ViewHolder {
		TextView mTitleText, mDescriptionText, mCountText;
	}
}
