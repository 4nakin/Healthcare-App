package com.example.healthcareapp.fragments;

import java.util.ArrayList;

import com.example.healthcareapp.R;
import com.example.healthcareapp.adapter.ExerciseScoreListAdapter;
import com.example.healthcareapp.model.ExerciseScoreListItem;
import com.example.healthcareapp.model.LineGraphData;
import com.example.healthcareapp.widgets.LineGraph;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StatisticsFragment extends Fragment {
	
	private View mViewHolder;
	private LineGraph mGraph;
	private ListView mScoreList;
	private ExerciseScoreListAdapter mAdapter;
	private int graphColor;
	private ArrayList<ExerciseScoreListItem> mData = new ArrayList<ExerciseScoreListItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mAdapter = new ExerciseScoreListAdapter(getActivity());
		mViewHolder = inflater.inflate(R.layout.fragment_statistics, null);
		mGraph = (LineGraph) mViewHolder.findViewById(R.id.statistics_graph);
		mScoreList = (ListView) mViewHolder.findViewById(R.id.statistics_exercises_score);
		initView();		
		return mViewHolder;
	}
	
	private void initView() {
		graphColor = getResources().getColor(R.color.pink_dark);
		//Dummy Data
		mGraph.addPlotData("1", new LineGraphData("1", 20.0f, "Jan-13", graphColor));
		mGraph.addPlotData("2", new LineGraphData("2", 10.0f, "Feb-13", graphColor));
		mGraph.addPlotData("3", new LineGraphData("3", 25.0f, "Mar-13", graphColor));
		mGraph.addPlotData("4", new LineGraphData("4", 30.0f, "Apr-13", graphColor));
		mGraph.addPlotData("5", new LineGraphData("5", 20.0f, "May-13", graphColor));
		mGraph.addPlotData("6", new LineGraphData("6", 10.0f, "Jun-13", graphColor));
		mGraph.addPlotData("7", new LineGraphData("7", 25.0f, "Jul-13", graphColor));
		mGraph.addPlotData("8", new LineGraphData("8", 30.0f, "Aug-13", graphColor));
		
		mData.add(new ExerciseScoreListItem(30, "Cleansing Breath"));
		mData.add(new ExerciseScoreListItem(54, "Low Calorie Diet"));
		mData.add(new ExerciseScoreListItem(67, "Energy Bar"));
		mData.add(new ExerciseScoreListItem(83, "Immune Booster"));
		mData.add(new ExerciseScoreListItem(60, "Natural Sleeping Aid"));
		mAdapter.setListData(mData);
		mScoreList.setAdapter(mAdapter);
	}
}
