package com.example.healthcareapp.interfaces;

import com.example.healthcareapp.model.SettingsListItem;

public interface OnSettingsListItemSelected {

	public static final int SETTINGS_CALIBRATE = 0;
	public static final int SETTINGS_DELETE = SETTINGS_CALIBRATE + 1;
	public static final int SETTINGS_CHANGE_PASSWORD = SETTINGS_CALIBRATE + 2;
	public static final int SETTINGS_LOGOUT = SETTINGS_CALIBRATE + 3;
	
	public void onSettingsListItemClicked(int which, SettingsListItem data);
}
