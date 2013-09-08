package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.adapter.QuestionnaireListAdapter;
import com.example.healthcareapp.interfaces.OnQuestionnaireCheckListItemSelected;
import com.example.healthcareapp.interfaces.OnQuestionnaireListItemSelected;
import com.example.healthcareapp.model.QuestionnaireItem;
import com.example.healthcareapp.util.QuestionnaireDataLoader;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class QuestionnaireListFragment extends ListFragment implements 
	LoaderCallbacks<ArrayList<QuestionnaireItem>>, OnQuestionnaireCheckListItemSelected {

	private final int LOADER_ID = 2;
		
	private QuestionnaireListAdapter mAdapter;
	
	private OnQuestionnaireListItemSelected mCallbacks;
	
	private ActionMode mActionMode;
	
	private ArrayList<QuestionnaireItem> mData = new ArrayList<QuestionnaireItem>();
	
	public QuestionnaireListFragment() { }
	
	private ActionMode.Callback modeCallback = new ActionMode.Callback() {
		
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}
		
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mCallbacks.onQuestionnaireListItemClicked(mData);
			mActionMode = null;
		}
		
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.setTitle(R.string.you_are_suffering_from);
			mode.getMenuInflater().inflate(R.menu.menu_questionnaire, menu);
			return true;
		}
		
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.menu_clear:
				clearListSelections();
				//mode.finish();
				//mActionMode = null;
				break;
			}
			return false;
		}
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new QuestionnaireListAdapter(getActivity(), this);
		setListAdapter(mAdapter);
		setListShown(false);
		setEmptyText(getString(R.string.no_data));
		getActivity().getLoaderManager().initLoader(LOADER_ID, null, this);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
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
	public Loader<ArrayList<QuestionnaireItem>> onCreateLoader(int id,
			Bundle args) {
		Loader<ArrayList<QuestionnaireItem>> mLoader = new QuestionnaireDataLoader(getActivity());
		mLoader.forceLoad();
		return mLoader;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<QuestionnaireItem>> arg0,
			ArrayList<QuestionnaireItem> data) {
		data.add(new QuestionnaireItem(getString(R.string.none_of_the_above)));
		mAdapter.setListData(data);
		mData.addAll(data);
    	if(isResumed())
    		setListShown(true);
    	else
    		setListShownNoAnimation(true);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<QuestionnaireItem>> arg0) {
		mAdapter.setListData(new ArrayList<QuestionnaireItem>());
		mData.clear();
	}

	@Override
	public void onQuestionnaireCheckListItemSelected(int pos,
			boolean isSelected) {
		if(mActionMode == null)
			mActionMode =getActivity().startActionMode(modeCallback);
		mData.get(pos).setSelected(isSelected);
	}
	
	private void clearListSelections() {
		for(int count = 0; count < getListView().getCount(); count++) {
			LinearLayout view = (LinearLayout) getListView().getChildAt(count);
			CheckBox cb = (CheckBox) view.getChildAt(1);
			cb.setChecked(false);
		}
	}
}
