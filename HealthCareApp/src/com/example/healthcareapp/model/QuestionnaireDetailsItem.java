package com.example.healthcareapp.model;

public class QuestionnaireDetailsItem {

	public static final int NORMAL = 0;
	public static final int  EDITABLE = 1;
	
	private int mQuestionID;
	private String mQuestion;
	private int mType;
	private String mAnswerText;
	private boolean isTrue;
	
	public QuestionnaireDetailsItem() { }
	
	/**
	 * 
	 * @param mQuestionID
	 */
	public QuestionnaireDetailsItem(int mQuestionID) {
		super();
		this.mQuestionID = mQuestionID;
	}
	
	/**
	 * @return the mQuestionID
	 */
	public int getQuestionID() {
		return mQuestionID;
	}

	/**
	 * @param mQuestionID the mQuestionID to set
	 */
	public void setQuestionID(int mQuestionID) {
		this.mQuestionID = mQuestionID;
	}

	/**
	 * @return the mQuestion
	 */
	public String getQuestion() {
		return mQuestion;
	}

	/**
	 * @param mQuestion the mQuestion to set
	 */
	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	/**
	 * @return the mType
	 */
	public int getType() {
		return mType;
	}

	/**
	 * @param mType the mType to set
	 */
	public void setType(int mType) {
		this.mType = mType;
	}

	/**
	 * @return the isTrue
	 */
	public boolean isTrue() {
		return isTrue;
	}

	/**
	 * @param isTrue the isTrue to set
	 */
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	/**
	 * @return the mAnswerText
	 */
	public String getAnswerText() {
		return mAnswerText;
	}

	/**
	 * @param mAnswerText the mAnswerText to set
	 */
	public void setAnswerText(String mAnswerText) {
		this.mAnswerText = mAnswerText;
	}
}
