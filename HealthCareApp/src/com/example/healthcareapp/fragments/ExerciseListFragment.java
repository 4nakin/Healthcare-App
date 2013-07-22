package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.adapter.ExerciseListAdapter;
import com.example.healthcareapp.interfaces.OnExerciseListItemSelected;
import com.example.healthcareapp.model.ExerciseItem;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class ExerciseListFragment extends ListFragment {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private OnExerciseListItemSelected mCallbacks = sDummyCallbacks;

	private int mActivatedPosition = ListView.INVALID_POSITION;
	
	private ArrayList<ExerciseItem> mData = new ArrayList<ExerciseItem>();
	
	private ExerciseListAdapter mAdapter;

	private static OnExerciseListItemSelected sDummyCallbacks = new OnExerciseListItemSelected() {
		@Override
		public void onListItemSelected(ExerciseItem data) {	}
	};

	public ExerciseListFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new ExerciseListAdapter(getActivity());
		mData.add(new ExerciseItem("Cleansing Breath", true, "Description", "http://www.youtube.com/watch?v=t7WFq17NxWA"));
		mData.add(new ExerciseItem("Low Calorie Diet", false, "Description", "http://www.youtube.com/watch?v=t7WFq17NxWA"));
		mData.add(new ExerciseItem("Energy Bar", true, "Description", "http://www.youtube.com/watch?v=t7WFq17NxWA"));
		mData.add(new ExerciseItem("Immune Booster", false, "Description", "http://www.youtube.com/watch?v=t7WFq17NxWA"));
		mData.add(new ExerciseItem("Natural Sleeping Aid", true, "Description", "http://www.youtube.com/watch?v=t7WFq17NxWA"));
		mAdapter.setListData(mData);
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
		getListView().setCacheColorHint(Color.TRANSPARENT);
		getListView().setPadding((int) getResources().getDimension(R.dimen.list_view_padding), 0, 
				(int) getResources().getDimension(R.dimen.list_view_padding), 0);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof OnExerciseListItemSelected)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}
		mCallbacks = (OnExerciseListItemSelected) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mCallbacks.onListItemSelected(mData.get(position));
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
