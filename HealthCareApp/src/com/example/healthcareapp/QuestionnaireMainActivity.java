package com.example.healthcareapp;

import com.example.healthcareapp.fragments.QuestionnaireListFragment;
import com.example.healthcareapp.interfaces.OnQuestionnaireListItemSelected;
import com.example.healthcareapp.model.QuestionnaireItem;
import com.healthcareapp.IOIyears.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class QuestionnaireMainActivity extends Activity 
	implements OnQuestionnaireListItemSelected {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		if(findViewById(R.id.questionnaire_list_fragment) == null) 
			getFragmentManager().beginTransaction()
				.replace(R.id.questionnaire_main_holder, new QuestionnaireListFragment())
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

	@Override
	public void onQuestionnaireListItemClicked(QuestionnaireItem data) {
		Intent mDetailsIntent = new Intent(this, QuestionnaireDetailActivity.class);
		mDetailsIntent.putExtra(QuestionnaireDetailActivity.QUESTINNAIRE_NAME, data.getQuestionnaireTitle());
		startActivity(mDetailsIntent);
	}
}
