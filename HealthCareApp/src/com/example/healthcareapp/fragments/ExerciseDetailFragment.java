package com.example.healthcareapp.fragments;

import java.io.IOException;

import com.example.healthcareapp.ExerciseDetailActivity;
import com.example.healthcareapp.MainActivity;
import com.example.healthcareapp.R;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * A fragment representing a single ExerciseItem detail screen. This fragment is
 * either contained in a {@link MainActivity} in two-pane mode (on
 * tablets) or a {@link ExerciseDetailActivity} on handsets.
 */
public class ExerciseDetailFragment extends Fragment {

	public static final String ARG_ITEM_NAME = "item_id";
	public static final String ARG_ITEM_URL = "item_url";
	public static final String ARG_IS_AUDIO = "isAudio";
	
	private View mViewHolder;
	private VideoView mVideo;
	private MediaController mController;
	private MediaPlayer mMediaPlayer;
	private String streamingURL;
	private boolean isAudio = false;
	
	public ExerciseDetailFragment() {}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_URL)) 
			streamingURL = getArguments().getString(ARG_ITEM_URL);		
		if(getArguments().containsKey(ARG_IS_AUDIO)) 
			isAudio = getArguments().getBoolean(ARG_IS_AUDIO, false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mController = new MediaController(getActivity());
		mViewHolder = inflater.inflate(R.layout.fragment_exerciseitem_detail, container, false);
		mVideo = (VideoView) mViewHolder.findViewById(R.id.exercise_video_view);	
		return mViewHolder;
	}
	
	private void handleStream(boolean isAudio) throws IllegalStateException, IOException {
		if(isAudio) {
			/*mVideo.setVisibility(View.GONE);
			//Temp implementation to play the file form local source
			AssetFileDescriptor mediaFile = getActivity().getAssets().openFd("song.mp3");
			//TODO Actual implementation
			mMediaPlayer = new MediaPlayer();
			//mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setDataSource(mediaFile.getFileDescriptor(),mediaFile.getStartOffset(),mediaFile.getLength());
			mediaFile.close();
			//mMediaPlayer.setDataSource(streamingURL);
			mMediaPlayer.prepare(); // might take long! (for buffering, etc)
			mMediaPlayer.start();*/
		} else {
			mVideo.setMediaController(mController);		
			mVideo.setVideoURI(Uri.parse(streamingURL));
			mVideo.start();
			mController.show();
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if(mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		try {
			handleStream(isAudio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
