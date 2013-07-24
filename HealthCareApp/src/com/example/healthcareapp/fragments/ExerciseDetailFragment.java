package com.example.healthcareapp.fragments;

import com.example.healthcareapp.ExerciseDetailActivity;
import com.example.healthcareapp.ExerciseSessionActivity;
import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * A fragment representing a single ExerciseItem detail screen. This fragment is
 * either contained in a {@link MainActivity} in two-pane mode (on
 * tablets) or a {@link ExerciseDetailActivity} on mobile phones.
 */
public class ExerciseDetailFragment extends Fragment {

	public static final String ARG_ITEM_NAME = "item_id";
	public static final String ARG_ITEM_URL = "item_url";
	public static final String ARG_ITEM_DESCRIPTION = "item_description";
	
	private View mViewHolder;
	private VideoView mVideo;
	private MediaController mController;
	
	public ExerciseDetailFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mController = new MediaController(getActivity());
		mViewHolder = inflater.inflate(R.layout.fragment_exerciseitem_detail, container, false);
		mVideo = (VideoView) mViewHolder.findViewById(R.id.exercise_video_view);	
		
		((TextView) mViewHolder.findViewById(R.id.exercise_detials_description)).setText(getArguments().getString(ARG_ITEM_DESCRIPTION));
		
		mViewHolder.findViewById(R.id.exercise_video_play_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mVideo.setMediaController(mController);		
				mVideo.setVideoURI(Uri.parse(getArguments().getString(ARG_ITEM_URL)));
				mVideo.start();
				mController.show();
				mViewHolder.findViewById(R.id.exercise_video_play_btn).setVisibility(View.GONE);
			}
		});
		
		mVideo.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				mViewHolder.findViewById(R.id.exercise_video_play_btn).setVisibility(View.VISIBLE);
			}
		});
		
		mViewHolder.findViewById(R.id.exercise_start_session_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ExerciseSessionActivity.class));
			}
		});
		
		return mViewHolder;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(mVideo.isPlaying())
			mVideo.stopPlayback();
		mViewHolder.findViewById(R.id.exercise_video_play_btn).setVisibility(View.VISIBLE);
	}
}
