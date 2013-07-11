package com.example.healthcareapp.threads;

import com.example.healthcareapp.R;
import com.example.healthcareapp.util.AppPreferences;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserTask extends AsyncTask<EditText, Void, Integer>{

	private final int SUCCESS = 0;
	private final int ERROR_EXCEPTION = 1;
	private final int ERROR_PASS_MISMATCH = 2;
	private final int ERROR_INPUT_INCOMPLETE = 3;
	
	private ProgressDialog mDialog;
	private OnRegisterProcessCompletedListener mCallback;
	private Context mContext;
	private AppPreferences mPrefs;
	
	public interface OnRegisterProcessCompletedListener {
		public void onRegisterSuccessful();
	}
	
	public RegisterUserTask(Context context, OnRegisterProcessCompletedListener callback) {
		mCallback = callback;
		mContext = context;
		mPrefs = new AppPreferences(context);
		mDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_DARK);
		mDialog.setMessage("Please wait...");
		mDialog.setCancelable(false);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog.show();
	}
	
	@Override
	protected Integer doInBackground(EditText... params) {
		if(params[0].getText().toString().trim().length() > 0 &&
				params[1].getText().toString().trim().length() > 0 &&
				params[2].getText().toString().trim().length() > 0 &&
				params[3].getText().toString().trim().length() > 0 &&
				params[4].getText().toString().trim().length() > 0 &&
				params[5].getText().toString().trim().length() > 0) {
			if(params[4].getText().toString().trim().equals(params[5].getText().toString().trim())) {
				//TODO Online upload logic to come here
				mPrefs.setFirstname(params[0].getText().toString().trim());
				mPrefs.setLastname(params[1].getText().toString().trim());
				mPrefs.setUsername(params[2].getText().toString().trim());
				mPrefs.setEmail(params[3].getText().toString().trim());
				mPrefs.setPassword(params[4].getText().toString().trim());
				return SUCCESS;
			} else
				return ERROR_PASS_MISMATCH;
		} else
			return ERROR_INPUT_INCOMPLETE;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		mDialog.dismiss();
		switch(result) {
		case SUCCESS:
			mPrefs.setLoggedIn();
			mCallback.onRegisterSuccessful();
			break;
		case ERROR_EXCEPTION:
			Toast.makeText(mContext, R.string.msg_registration_failed, Toast.LENGTH_LONG).show();
			break;
		case ERROR_INPUT_INCOMPLETE:
			Toast.makeText(mContext, R.string.msg_registration_empty_field, Toast.LENGTH_LONG).show();
			break;
		case ERROR_PASS_MISMATCH:
			Toast.makeText(mContext, R.string.msg_registration_password_mismatch, Toast.LENGTH_LONG).show();
			break;
		}			
	}

}
