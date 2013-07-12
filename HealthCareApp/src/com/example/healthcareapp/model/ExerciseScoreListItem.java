package com.example.healthcareapp.model;

public class ExerciseScoreListItem {

	private int mScore;
	private String mName;
	
	/**
	 * @param mScore
	 * @param mName
	 */
	public ExerciseScoreListItem(int mScore, String mName) {
		super();
		this.mScore = mScore;
		this.mName = mName;
	}
	
	/**
	 * @return the mScore
	 */
	public int getScore() {
		return mScore;
	}
	
	/**
	 * @param mScore the mScore to set
	 */
	public void setScore(int mScore) {
		this.mScore = mScore;
	}
	
	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}	
}
