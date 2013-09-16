package com.example.healthcareapp.adapter;

import java.util.ArrayList;

import com.example.healthcareapp.model.CommentsItem;
import com.healthcareapp.IOIyears.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentsListAdapter extends BaseAdapter {

	private ArrayList<CommentsItem> mData = new ArrayList<CommentsItem>();
	private LayoutInflater mInflater;
	
	public CommentsListAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setListData(ArrayList<CommentsItem> data) {
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
			convertView = mInflater.inflate(R.layout.adapter_comments_list, null);
			holder.mCommentsText = (TextView) convertView.findViewById(R.id.adapter_comments_text);
			holder.mUsername = (TextView) convertView.findViewById(R.id.adapter_comments_username_text);
			convertView.setTag(holder);
		} else 
			holder = (ViewHolder) convertView.getTag();
		
		holder.mCommentsText.setText(mData.get(position).getComment());
		holder.mUsername.setText(mData.get(position).getUsername());
		if(mData.get(position).isLiked())
			holder.mCommentsText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_like, 0, 0, 0);
		else
			holder.mCommentsText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_dislike, 0, 0, 0);
		return convertView;
	}

	static class ViewHolder {
		TextView mCommentsText, mUsername;
	}

}
