package com.example.healthcareapp.threads;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.util.AppPreferences;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

public class AuthenticateUserTask extends AsyncTask<EditText, Void, Integer>{

	private final int SUCCESS = 1;
	private final int ERROR_CREDENTIALS = 2;
	private final int ERROR_EMPTY_FIELD = 3;
	private final int ERROR_EXCEPTION = 4;
	
	private ProgressDialog mProgress;
	private OnAuthenticateUserListener mCallback;
	private Context mContext;
	private AppPreferences mPrefs;
	
	public interface OnAuthenticateUserListener {
		public void onAuthenticationSuccess();
	}
	
	public AuthenticateUserTask(Context context, OnAuthenticateUserListener callback) {
		mCallback = callback;
		mContext = context;
		mPrefs = new AppPreferences(context);
		mProgress = new ProgressDialog(context, ProgressDialog.THEME_HOLO_DARK);
		mProgress.setMessage("Authenticating...");
		mProgress.setCancelable(false);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgress.show();
	}
	
	@Override
	protected Integer doInBackground(EditText... params) {
		if(params[0].getText().toString().trim().length() > 0 &&
				params[1].getText().toString().trim().length() > 0) 
			//TODO do online check
			if(mPrefs.getUsername() != null && mPrefs.getPassword() != null) 
				if(params[0].getText().toString().trim().equals(mPrefs.getUsername()) &&
						params[1].getText().toString().trim().equals(mPrefs.getPassword()))
					return SUCCESS;
				else
					return ERROR_CREDENTIALS;
			else
				return ERROR_CREDENTIALS;
		else
			return ERROR_EMPTY_FIELD;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		mProgress.dismiss();
		switch(result) {
		case SUCCESS:
			mPrefs.setLoggedIn();
			mCallback.onAuthenticationSuccess();
			break;
		case ERROR_EMPTY_FIELD:
			Toast.makeText(mContext, R.string.msg_login_empty_fields, Toast.LENGTH_LONG).show();
			break;
		case ERROR_CREDENTIALS:
			Toast.makeText(mContext, R.string.msg_login_wrong_credential, Toast.LENGTH_LONG).show();
			break;
		case ERROR_EXCEPTION:
			Toast.makeText(mContext, R.string.msg_login_failed, Toast.LENGTH_LONG).show();
			break;
		}
	}
}
