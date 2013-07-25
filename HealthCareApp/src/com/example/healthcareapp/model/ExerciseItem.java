package com.example.healthcareapp.model;

public class ExerciseItem {

	private String mExerciseName;
	private String mExerciseDescription;
	private String mExerciseVideoURL;
	private String mExerciseAudioURL;

	public ExerciseItem() {
	}

	/**
	 * @param mExerciseName
	 * @param isAudio
	 * @param mExerciseDescription
	 * @param mExerciseVideoURL
	 */
	public ExerciseItem(String mExerciseName, String mExerciseDescription,
			String mExerciseVideoURL, String mExerciseAudioURL) {
		super();
		this.mExerciseName = mExerciseName;
		this.mExerciseDescription = mExerciseDescription;
		this.mExerciseVideoURL = mExerciseVideoURL;
		this.mExerciseAudioURL = mExerciseAudioURL;
	}

	/**
	 * @return the mExerciseName
	 */
	public String getExerciseName() {
		return mExerciseName;
	}

	/**
	 * @param mExerciseName
	 *            the mExerciseName to set
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
	 * @param mExerciseVideoURL
	 *            the mExerciseVideoURL to set
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
	 * @param mExerciseDescription
	 *            the mExerciseDescription to set
	 */
	public void setExerciseDescription(String mExerciseDescription) {
		this.mExerciseDescription = mExerciseDescription;
	}

	/**
	 * @return the mExerciseAudioURL
	 */
	public String getExerciseAudioURL() {
		return mExerciseAudioURL;
	}

	/**
	 * @param mExerciseAudioURL the mExerciseAudioURL to set
	 */
	public void setExerciseAudioURL(String mExerciseAudioURL) {
		this.mExerciseAudioURL = mExerciseAudioURL;
	}
}
