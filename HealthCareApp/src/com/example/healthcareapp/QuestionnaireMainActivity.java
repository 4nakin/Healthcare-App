package com.example.healthcareapp;

import java.util.ArrayList;

import com.example.healthcareapp.fragments.QuestionnaireListFragment;
import com.example.healthcareapp.interfaces.OnQuestionnaireListItemSelected;
import com.example.healthcareapp.model.QuestionnaireItem;
import com.healthcareapp.IOIyears.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class QuestionnaireMainActivity extends Activity 
	implements OnQuestionnaireListItemSelected {
	
	private boolean isNoneSelected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setSubtitle(R.string.please_select);
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
	public void onQuestionnaireListItemClicked(ArrayList<QuestionnaireItem> data) {
		int count = 0;
		for(QuestionnaireItem item : data) {
			if(item.isSelected()) {
				if(item.getQuestionnaireTitle().equals(getString(R.string.none_of_the_above)))
						isNoneSelected = true;
				count++;
			}
		}
		if(count > 0) { 
			if(!isNoneSelected)
				startActivity(new Intent(this, QuestionnaireDetailActivity.class));
			finish(); 
		} else 
			Toast.makeText(this, R.string.msg_select_questionnaire_items, Toast.LENGTH_LONG).show();
	}
}
