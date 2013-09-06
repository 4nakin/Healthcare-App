package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.adapter.QuestionnaireListAdapter;
import com.example.healthcareapp.interfaces.OnQuestionnaireListItemSelected;
import com.example.healthcareapp.model.QuestionnaireItem;
import com.example.healthcareapp.util.QuestionnaireDataLoader;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class QuestionnaireListFragment extends ListFragment implements LoaderCallbacks<ArrayList<QuestionnaireItem>>{

	private final int LOADER_ID = 2;
	
	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	
	private int mActivatedPosition = ListView.INVALID_POSITION;
	
	private QuestionnaireListAdapter mAdapter;
	
	private OnQuestionnaireListItemSelected mCallbacks;
	
	public QuestionnaireListFragment() { }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new QuestionnaireListAdapter(getActivity());
		setListAdapter(mAdapter);
		setListShown(false);
		setEmptyText(getString(R.string.no_data));
		getActivity().getLoaderManager().initLoader(LOADER_ID, null, this);
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
		if (!(activity instanceof OnQuestionnaireListItemSelected)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}
		mCallbacks = (OnQuestionnaireListItemSelected) activity;
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mCallbacks.onQuestionnaireListItemClicked(mAdapter.getItem(position));
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

	@Override
	public Loader<ArrayList<QuestionnaireItem>> onCreateLoader(int id,
			Bundle args) {
		Loader<ArrayList<QuestionnaireItem>> mLoader = new QuestionnaireDataLoader(getActivity());
		mLoader.forceLoad();
		return mLoader;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<QuestionnaireItem>> arg0,
			ArrayList<QuestionnaireItem> data) {
		mAdapter.setListData(data);
    	if(isResumed())
    		setListShown(true);
    	else
    		setListShownNoAnimation(true);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<QuestionnaireItem>> arg0) {
		mAdapter.setListData(new ArrayList<QuestionnaireItem>());
	}
}
