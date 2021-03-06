package com.example.healthcareapp;

import java.io.IOException;

import com.example.healthcareapp.db.DBAdapter;
import com.example.healthcareapp.fragments.ExerciseDetailFragment;
import com.example.healthcareapp.util.AinimationUtils;
import com.example.healthcareapp.views.SessionResultWindow;
import com.healthcareapp.IOIyears.R;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ExerciseSessionActivity extends Activity {

	private MediaPlayer mMediaPlayer;
	private ViewFlipper mFooterFlipper;
	private TextView mMinutes, mSeconds, mPauseBtn, mSessionHandlerText;
	
	private long startTime = 0L, pauseStartTime = 0L, pauseEndTime = 0L;
	private Handler mSessionTimerHandler = new Handler();
	private long timeInMilliseconds = 0L, mRecommendedSessionTime = 0L;
	private boolean isPaused = false;
	private SessionResultWindow mResultDialog;
	private DBAdapter mDBAdapter;
	private AinimationUtils mAnimUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_session);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		
		mAnimUtils = new AinimationUtils();
		mDBAdapter = new DBAdapter(this);
		mResultDialog = new SessionResultWindow(this);
		mFooterFlipper = (ViewFlipper) findViewById(R.id.session_footer_flipper);
		mMinutes = (TextView) findViewById(R.id.session_process_minutes_text);
		mSeconds = (TextView) findViewById(R.id.session_process_seconds_text);
		mPauseBtn = (TextView) findViewById(R.id.session_pause_btn);
		mSessionHandlerText = (TextView) findViewById(R.id.session_process_message_container);
		
		mRecommendedSessionTime = getIntent().getLongExtra(ExerciseDetailFragment.ARG_SESSION_TIME, 0L);
		mSessionHandlerText.setText(getProposedTime(mRecommendedSessionTime));
						
		
		
	    findViewById(R.id.session_start_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mFooterFlipper.showNext();
				startTime = SystemClock.uptimeMillis();
				mSessionTimerHandler.postDelayed(updateTimerThread, 0);
				mSessionHandlerText.setText(R.string.session_active);
			}
		});
		
		mPauseBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isPaused) {
					mPauseBtn.setText(R.string.pause_session);
					pauseEndTime = SystemClock.uptimeMillis();
					startTime += (pauseEndTime - pauseStartTime);
					mSessionTimerHandler.postDelayed(updateTimerThread, 0);
					mSessionHandlerText.setText(R.string.session_active);
					isPaused = false;
				} else {
					mPauseBtn.setText(R.string.resume_session);
					pauseStartTime = SystemClock.uptimeMillis();
					mSessionTimerHandler.removeCallbacks(updateTimerThread);
					mSessionHandlerText.setText(R.string.session_paused);
					isPaused = true;
				}
			}
		});
		
		findViewById(R.id.session_end_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int sessionScore = getSessionScore(mRecommendedSessionTime, timeInMilliseconds);
				mFooterFlipper.showPrevious();
				startTime = pauseStartTime = pauseEndTime = 0L;
				mSessionTimerHandler.removeCallbacks(updateTimerThread);
				mMinutes.startAnimation(mAnimUtils.blinkAnim(500, 5));
				mSeconds.startAnimation(mAnimUtils.blinkAnim(500, 5));
				mSessionHandlerText.setText(getProposedTime(mRecommendedSessionTime));
				mResultDialog.setScore(sessionScore);
				mResultDialog.showAtLocation(mSessionHandlerText, Gravity.CENTER, 0, 0);
				mDBAdapter.insertNewScore(
						getIntent().getStringExtra(ExerciseDetailFragment.ARG_ITEM_ID), 
						getIntent().getStringExtra(ExerciseDetailFragment.ARG_ITEM_NAME), 
						sessionScore, timeInMilliseconds);
			}
		});
		
		mResultDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				mMinutes.setText(R.string.zero);
				mSeconds.setText(R.string.zero);
			}
		});
	}
	
	private String getProposedTime(long time) {
		int secs = (int) (time / 1000);
		int mins = secs / 60;
		secs = secs % 60;
		String text = getString(R.string.msg_start_session_init) 
				+ "\nOptimal session time is " 
				+ String.format("%02d", mins) + ":" 
				+ String.format("%02d", secs) + "mins";
		return text;
	}
	
	private int getSessionScore(long proposedTime, long sessionTime) {
		if(sessionTime == 0L)
			return 0;
		else if(sessionTime > proposedTime)
			return 100;
		else 
			return (int) ((sessionTime*100) / proposedTime);
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
	protected void onResume() {
		super.onResume();
		mDBAdapter.openScoreDB();
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
		mDBAdapter.closeScoreDB();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		mDBAdapter.closeScoreDB();
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
		
	private Runnable updateTimerThread = new Runnable() {
		@Override
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			int secs = (int) (timeInMilliseconds / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			mMinutes.setText(String.format("%02d", mins));
			mSeconds.setText(String.format("%02d", secs));
			mSessionTimerHandler.postDelayed(this, 0);
		}
	};
}
