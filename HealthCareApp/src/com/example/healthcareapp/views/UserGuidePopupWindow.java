package com.example.healthcareapp.views;

import com.example.healthcareapp.R;
import com.example.healthcareapp.util.AppPreferences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public class UserGuidePopupWindow extends PopupWindow {

	public static final int MAIN_MENU_GUIDE = 1;
	
	private LayoutInflater mInflater;
	private View mViewHolder;
	private int currentGuideView = 1;
	private AppPreferences mPrefs;
	
	public UserGuidePopupWindow(Context context) {
		super(context);
		mPrefs = new AppPreferences(context);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mViewHolder = mInflater.inflate(R.layout.layout_user_guide, null);
		setContentView(mViewHolder);
		setHeight(LayoutParams.MATCH_PARENT);
		setWidth(LayoutParams.MATCH_PARENT);
		setFocusable(true);
		
		mViewHolder.findViewById(R.id.user_popup_close_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (currentGuideView) {
				case MAIN_MENU_GUIDE:
					mPrefs.setMenuToggleGuideShown();
					break;
				}
				dismiss();
			}
		});
	}
	
	public void setGuideView(int which) {
		currentGuideView = which;
		switch (which) {
		case MAIN_MENU_GUIDE:
			((FrameLayout) mViewHolder.findViewById(R.id.user_popup_container))
				.addView(mInflater.inflate(R.layout.layout_main_menu_guide, null));
			break;
		}
	}
}
