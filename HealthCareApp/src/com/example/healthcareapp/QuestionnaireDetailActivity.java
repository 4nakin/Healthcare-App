package com.example.healthcareapp;

import com.example.healthcareapp.fragments.QuestionnaireDetailsFragment;
import com.healthcareapp.IOIyears.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class QuestionnaireDetailActivity extends Activity {
	
	public static final String QUESTINNAIRE_NAME = "questionnaire_name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		
		getFragmentManager()
			.beginTransaction()
			.replace(R.id.questionnaire_details_holder, new QuestionnaireDetailsFragment())
			.commit();
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
}
