package com.example.healthcareapp.model;

public class MainMenuListItem {

	private int mImageID;
	private String mName;
	
	public MainMenuListItem() { }

	/**
	 * @param mImageID
	 * @param mName
	 */
	public MainMenuListItem(int mImageID, String mName) {
		super();
		this.mImageID = mImageID;
		this.mName = mName;
	}

	/**
	 * @return the mImageID
	 */
	public int getImageID() {
		return mImageID;
	}

	/**
	 * @param mImageID the mImageID to set
	 */
	public void setImageID(int mImageID) {
		this.mImageID = mImageID;
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
