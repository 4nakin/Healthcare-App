package com.example.healthcareapp.fragments;

import com.example.healthcareapp.ExerciseDetailActivity;
import com.example.healthcareapp.ExerciseSessionActivity;
import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.views.CommentsWindow;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.healthcareapp.IOIyears.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single ExerciseItem detail screen. This fragment is
 * either contained in a {@link MainActivity} in two-pane mode (on
 * tablets) or a {@link ExerciseDetailActivity} on mobile phones.
 */
public class ExerciseDetailFragment extends Fragment {

	private final String YOUTUBE_API_KEY = "AIzaSyC9HL3mxOUTalO160976FSUPb-gQv-425E";
	
	public static final String ARG_ITEM_NAME = "item_id";
	public static final String ARG_VIDEO_URL = "item_url";
	public static final String ARG_ITEM_DESCRIPTION = "item_description";
	public static final String ARG_SESSION_TIME = "session_time";
	
	private View mViewHolder;
	private YouTubePlayerView mVideo;
	private CommentsWindow mCommentsWindow;
	
	public ExerciseDetailFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mCommentsWindow = new CommentsWindow(getActivity());
		mViewHolder = inflater.inflate(R.layout.fragment_exerciseitem_detail, container, false);
		mVideo = (YouTubePlayerView) mViewHolder.findViewById(R.id.exercise_video_view);	
		mVideo.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
			@Override
			public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
					boolean wasRestored) {
				if (!wasRestored) 
				      player.cueVideo(getArguments().getString(ARG_VIDEO_URL));
			}
			
			@Override
			public void onInitializationFailure(Provider arg0,
					YouTubeInitializationResult arg1) {
				
			}
		});
		
		((TextView) mViewHolder.findViewById(R.id.exercise_detials_description)).setText(getArguments().getString(ARG_ITEM_DESCRIPTION));
						
		mViewHolder.findViewById(R.id.exercise_start_session_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ExerciseSessionActivity.class); 
				intent.putExtra(ExerciseDetailFragment.ARG_SESSION_TIME, 
						getArguments().getLong(ExerciseDetailFragment.ARG_SESSION_TIME));
				startActivity(intent);
			}
		});
		
		mViewHolder.findViewById(R.id.exercise_comments_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCommentsWindow.showAtLocation(
						mViewHolder.findViewById(R.id.exercise_comments_btn), 
						Gravity.NO_GRAVITY, 0, 0);				
			}
		});
		
		return mViewHolder;
	}
}
