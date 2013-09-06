package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.model.QuestionnaireItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionnaireListAdapter extends BaseAdapter {

	private ArrayList<QuestionnaireItem> mData = new ArrayList<QuestionnaireItem>();
	private LayoutInflater mInflater;
	
	public QuestionnaireListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<QuestionnaireItem> data) {
		mData = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public QuestionnaireItem getItem(int pos) {
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
			convertView = mInflater.inflate(R.layout.adapter_questionnaire_list, null);
			holder.mTitleText = (TextView) convertView.findViewById(R.id.questionnaire_title);
			convertView.setTag(holder);
		} else 
			holder  = (ViewHolder) convertView.getTag();
		
		holder.mTitleText.setText(mData.get(position).getQuestionnaireTitle());
		return convertView;
	}

	static class ViewHolder {
		TextView mTitleText;
	}
}
