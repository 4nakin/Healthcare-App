package com.example.healthcareapp.model;

public class QuestionnaireItem {

	private String mQuestionnaireTitle;
	
	public QuestionnaireItem() { }

	/**
	 * @param mQuestionnaireTitle
	 */
	public QuestionnaireItem(String mQuestionnaireTitle) {
		super();
		this.mQuestionnaireTitle = mQuestionnaireTitle;
	}

	/**
	 * @return the mQuestionnaireTitle
	 */
	public String getQuestionnaireTitle() {
		return mQuestionnaireTitle;
	}

	/**
	 * @param mQuestionnaireTitle the mQuestionnaireTitle to set
	 */
	public void setQuestionnaireTitle(String mQuestionnaireTitle) {
		this.mQuestionnaireTitle = mQuestionnaireTitle;
	}
}
