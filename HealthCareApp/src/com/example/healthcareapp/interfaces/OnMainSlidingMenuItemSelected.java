package com.example.healthcareapp.interfaces;

public interface OnMainSlidingMenuItemSelected {

	public static final int EDIT_PROFILE = 1;
	public static final int COURSES = EDIT_PROFILE + 1;
	public static final int STATISTICS = COURSES + 1;
	public static final int SETTINGS = STATISTICS + 1;
	public static final int ABOUT = SETTINGS + 1;
	
	public void OnMenuItemSelected(int which);
}
