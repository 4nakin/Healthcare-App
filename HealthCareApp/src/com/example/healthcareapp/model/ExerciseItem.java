package com.example.healthcareapp.model;

public class ExerciseItem {

	private String mExerciseName;
	private boolean isAudio;
	private String mExerciseDescription;
	private String mExerciseVideoURL;
	
	public ExerciseItem() { }
		
	/**
	 * @param mExerciseName
	 * @param isAudio
	 * @param mExerciseDescription
	 * @param mExerciseVideoURL
	 */
	public ExerciseItem(String mExerciseName, boolean isAudio,
			String mExerciseDescription, String mExerciseVideoURL) {
		super();
		this.mExerciseName = mExerciseName;
		this.isAudio = isAudio;
		this.mExerciseDescription = mExerciseDescription;
		this.mExerciseVideoURL = mExerciseVideoURL;
	}

	/**
	 * @return the mExerciseName
	 */
	public String getExerciseName() {
		return mExerciseName;
	}
	
	/**
	 * @param mExerciseName the mExerciseName to set
	 */
	public void setExerciseName(String mExerciseName) {
		this.mExerciseName = mExerciseName;
	}
	
	/**
	 * @return the mExerciseVideoURL
	 */
	public String getExerciseVideoURL() {
		return mExerciseVideoURL;
	}
	
	/**
	 * @param mExerciseVideoURL the mExerciseVideoURL to set
	 */
	public void setExerciseVideoURL(String mExerciseVideoURL) {
		this.mExerciseVideoURL = mExerciseVideoURL;
	}

	/**
	 * @return the mExerciseDescription
	 */
	public String getExerciseDescription() {
		return mExerciseDescription;
	}

	/**
	 * @param mExerciseDescription the mExerciseDescription to set
	 */
	public void setmExerciseDescription(String mExerciseDescription) {
		this.mExerciseDescription = mExerciseDescription;
	}

	/**
	 * @return the isAudio
	 */
	public boolean isAudio() {
		return isAudio;
	}

	/**
	 * @param isAudio the isAudio to set
	 */
	public void setAudio(boolean isAudio) {
		this.isAudio = isAudio;
	}
}
