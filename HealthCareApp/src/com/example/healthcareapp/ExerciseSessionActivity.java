package com.example.healthcareapp;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import com.healthcareapp.IOIyears.R;

public class ExerciseSessionActivity extends Activity {

	private MediaPlayer mMediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_session);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
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
	
	public void handleStream(boolean isAudio) throws IllegalStateException, IOException {
		//Temp implementation to play the file form local source
		AssetFileDescriptor mediaFile = getAssets().openFd("song.mp3");
		//TODO Actual implementation
		mMediaPlayer = new MediaPlayer();
		//mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setDataSource(mediaFile.getFileDescriptor(),mediaFile.getStartOffset(),mediaFile.getLength());
		mediaFile.close();
		//mMediaPlayer.setDataSource(streamingURL);
		mMediaPlayer.prepare(); // might take long! (for buffering, etc)
		mMediaPlayer.start();
	}
}
