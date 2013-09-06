package com.example.healthcareapp;

import com.example.healthcareapp.fragments.CalibrateSettingsFragment;
import com.example.healthcareapp.fragments.ChangePasswordFragment;
import com.example.healthcareapp.interfaces.OnSettingsListItemSelected;
import com.healthcareapp.IOIyears.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsDetailsActivity extends Activity {

	public static String WHICH_SETTINGS = "which";
	public static String SETTINGS_NAME = "name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_details);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		getActionBar().setTitle(getIntent().getStringExtra(SETTINGS_NAME));
		getActionBar().setSubtitle(R.string.settings);
		
		switch(getIntent().getIntExtra(WHICH_SETTINGS, OnSettingsListItemSelected.SETTINGS_CALIBRATE)) {
		case OnSettingsListItemSelected.SETTINGS_CALIBRATE:
			getFragmentManager().beginTransaction()
				.replace(R.id.settings_details_fragment_holder, new CalibrateSettingsFragment())
				.commit();
			break;
		case OnSettingsListItemSelected.SETTINGS_DELETE:
			break;
		case OnSettingsListItemSelected.SETTINGS_CHANGE_PASSWORD:
			getFragmentManager().beginTransaction()
				.replace(R.id.settings_details_fragment_holder, new ChangePasswordFragment())
				.commit();
			break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
