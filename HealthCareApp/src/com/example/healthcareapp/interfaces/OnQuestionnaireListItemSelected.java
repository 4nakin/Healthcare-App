package com.example.healthcareapp.interfaces;

import java.util.ArrayList;

import com.example.healthcareapp.model.QuestionnaireItem;

public interface OnQuestionnaireListItemSelected {
	public void onQuestionnaireListItemClicked(ArrayList<QuestionnaireItem> data);
}
