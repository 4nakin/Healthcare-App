package com.example.healthcareapp.fragments;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.util.SoundSensorUtil;
import com.example.healthcareapp.widgets.RadialProgressView;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalibrateSettingsFragment extends Fragment {

	private static final int POLL_INTERVAL = 100;
	private Handler mHandler = new Handler();
	
	private View mViewHolder;
	private RadialProgressView mMeterView;
	private SoundSensorUtil mSensor;
	private double mAmplitude;
	private boolean isSensorOn = false;
	private Button mProcessButton;
	private TextView mProcessMessage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mSensor = new SoundSensorUtil();
		mViewHolder = inflater.inflate(R.layout.fragment_calibrate, null);
		mMeterView = (RadialProgressView) mViewHolder.findViewById(R.id.calibrate_progress_view);
		mProcessButton = (Button) mViewHolder.findViewById(R.id.calibrate_process_control_btn);
		mProcessMessage = (TextView) mViewHolder.findViewById(R.id.calibrate_process_message_container);
		
		mProcessButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isSensorOn) 
					stopCalibrating();
				else 
					startCalibrating();
			}
		});
		return mViewHolder;
	}
	
	private Runnable mPollTask = new Runnable() {
        public void run() {
        	mAmplitude = mSensor.getAmplitude();
                Log.i("Noise Alert", "Value : " + String.valueOf(mAmplitude));
                mMeterView.setCurrentValue((int) mAmplitude, true);
                mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }
	};
	
	private void startCalibrating() {
		try {
			mSensor.start();
			Log.i("Noise Alert", "MAX Value : " + String.valueOf(mSensor.getMaximumAmplitude()));
			mMeterView.setMaxValue(mSensor.getMaximumAmplitude());
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);
			mProcessButton.setText(R.string.save_value);
			mProcessMessage.setText(R.string.msg_calibrating);
			isSensorOn = true;
		} catch (Exception e) { 
			e.printStackTrace(); 
			Toast.makeText(getActivity(), R.string.msg_sensor_error, Toast.LENGTH_SHORT).show();
		}
	}
	
	private void stopCalibrating() {
		mHandler.removeCallbacks(mPollTask);
		mSensor.stop();
		mProcessButton.setText(R.string.start);
		mProcessMessage.setText(R.string.msg_waiting_for_input);
		isSensorOn = false;
	}
}
