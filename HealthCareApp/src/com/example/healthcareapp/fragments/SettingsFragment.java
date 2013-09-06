package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.adapter.SettingsListAdapter;
import com.example.healthcareapp.interfaces.OnSettingsListItemSelected;
import com.example.healthcareapp.model.SettingsListItem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SettingsFragment extends ListFragment {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;
	private ArrayList<SettingsListItem> mSettingsData = new ArrayList<SettingsListItem>();
	private SettingsListAdapter mAdapter;
	private OnSettingsListItemSelected mCallback;
	
	public SettingsFragment() { }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new SettingsListAdapter(getActivity());
		mSettingsData.add(new SettingsListItem("Calibrate", "Calibrate your device for breath meter readings", R.drawable.ic_action_claibrate));
		mSettingsData.add(new SettingsListItem("Clear Data", "Erase all exercise statistics stored by the application and server", R.drawable.ic_action_delete));
		mSettingsData.add(new SettingsListItem("Change Password", "Change your application password", R.drawable.ic_action_password));
		mSettingsData.add(new SettingsListItem("Help", "A walkthrough on how to use 101 Years", R.drawable.ic_action_help));
		mSettingsData.add(new SettingsListItem("Logout", "Logout from your current session", R.drawable.ic_action_logout));
		mAdapter.setListData(mSettingsData);
		setListAdapter(mAdapter);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
				.getInt(STATE_ACTIVATED_POSITION));
		}
		getListView().setPadding((int) getResources().getDimension(R.dimen.list_view_padding), 0, 
				(int) getResources().getDimension(R.dimen.list_view_padding), 0);
	};	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		//TODO change from position to a more meaningful value
		mCallback.onSettingsListItemClicked(position, mSettingsData.get(position));
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof OnSettingsListItemSelected)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}
		mCallback = (OnSettingsListItemSelected) activity;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}
		mActivatedPosition = position;
	}
}
