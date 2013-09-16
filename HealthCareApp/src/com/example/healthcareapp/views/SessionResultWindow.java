package com.example.healthcareapp.views;

import com.example.healthcareapp.widgets.RadialProgressView;
import com.healthcareapp.IOIyears.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SessionResultWindow extends PopupWindow {

	private LayoutInflater mInflater;
	private View mViewHolder;
	private TextView mScoreText;
	private RadialProgressView mScoreView;
	private int mScore;
	
	public SessionResultWindow(Context context) {
		super(context);		
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mViewHolder = mInflater.inflate(R.layout.layout_session_score_popup, null);
		mScoreText = (TextView) mViewHolder.findViewById(R.id.session_score_text);
		mScoreView = (RadialProgressView) mViewHolder.findViewById(R.id.session_progress_view);
		setContentView(mViewHolder);
		setHeight(LayoutParams.WRAP_CONTENT);
		setWidth(LayoutParams.MATCH_PARENT);
		setBackgroundDrawable(context.getResources().getDrawable(R.drawable.slider_bg));
		//setBackgroundDrawable(null);
		setFocusable(true);
		
		mScoreView.setMaxValue(100);
		
		mViewHolder.findViewById(R.id.session_details_popup_close).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		mViewHolder.findViewById(R.id.session_facebook_share).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		mViewHolder.findViewById(R.id.session_google_share).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}
	
	public void setScore(int score) {
		mScore = score;
		mScoreText.setText(String.valueOf(score));
	}
	
	@Override
	public void dismiss() {
		super.dismiss();
		mScoreView.setCurrentValue(0, false);
	}
	
	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		mScoreView.setCurrentValue(mScore, true);
	}
}
