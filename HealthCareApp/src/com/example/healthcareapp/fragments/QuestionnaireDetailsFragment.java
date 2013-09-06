package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.model.QuestionnaireDetailsItem;
import com.example.healthcareapp.util.QuestionnaireDetialsLoader;
import com.example.healthcareapp.views.QuestionnaireView;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
public class QuestionnaireDetailsFragment extends Fragment 
	implements LoaderCallbacks<ArrayList<QuestionnaireDetailsItem>>{

	private final int LOADER_ID = 3;
	
	private View mHolderView;
	private ScrollView mQuestionsContainer;
	private TextView mEmptyList;
	private ProgressBar mLoading;
	private LinearLayout mQuestionsList;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getLoaderManager().initLoader(LOADER_ID, null, this);
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mHolderView = inflater.inflate(R.layout.fragment_questionnaire_detials, null);
		mQuestionsList = (LinearLayout) mHolderView.findViewById(R.id.questionnaire_details_list);			
		mQuestionsContainer = (ScrollView) mHolderView.findViewById(R.id.questionnaire_details_scroll_container);
		mEmptyList = (TextView) mHolderView.findViewById(R.id.questionnaire_details_empty);
		mLoading = (ProgressBar) mHolderView.findViewById(R.id.questionnaire_detials_loading_progress);
		return mHolderView;
	}
	
	@Override
	public Loader<ArrayList<QuestionnaireDetailsItem>> onCreateLoader(int id,
			Bundle args) {
		Loader<ArrayList<QuestionnaireDetailsItem>> mLoader = new QuestionnaireDetialsLoader(getActivity());
		mLoader.forceLoad();
		return mLoader;
	}

	@Override
	public void onLoadFinished(
			Loader<ArrayList<QuestionnaireDetailsItem>> arg0,
			ArrayList<QuestionnaireDetailsItem> listData) {
		setListData(listData);	
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<QuestionnaireDetailsItem>> arg0) {
		mQuestionsList.removeAllViews();
		showProgress();
	}
	
	private void setListData(ArrayList<QuestionnaireDetailsItem> mData) {
		if(mData != null && mData.size() > 0) {
			mQuestionsContainer.setVisibility(View.VISIBLE);
			mLoading.setVisibility(View.GONE);
			for(QuestionnaireDetailsItem data : mData) {
				QuestionnaireView view = new QuestionnaireView(getActivity(), data);
				mQuestionsList.addView(view.getBaseView());
			}
		} else {
			mLoading.setVisibility(View.GONE);
			mEmptyList.setVisibility(View.VISIBLE);
		}
	}
	
	private void showProgress() {
		mLoading.setVisibility(View.VISIBLE);
		mEmptyList.setVisibility(View.GONE);
		mQuestionsContainer.setVisibility(View.GONE);
	}
}
