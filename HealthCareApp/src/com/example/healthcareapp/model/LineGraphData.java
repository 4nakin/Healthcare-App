package com.example.healthcareapp.model;

public class LineGraphData {

	private String mItemID;
	private float mValue;
	private String mPlotName;
	private int mColor;
	
	public LineGraphData() { }

	/**
	 * @param mItemID
	 * @param mValue
	 * @param mPlotName
	 * @param mColor
	 */
	public LineGraphData(String mItemID, float mValue, String mPlotName,
			int mColor) {
		super();
		this.mItemID = mItemID;
		this.mValue = mValue;
		this.mPlotName = mPlotName;
		this.mColor = mColor;
	}

	/**
	 * @return the mItemID
	 */
	public String getItemID() {
		return mItemID;
	}

	/**
	 * @param mItemID the mItemID to set
	 */
	public void setItemID(String mItemID) {
		this.mItemID = mItemID;
	}

	/**
	 * @return the mValue
	 */
	public float getValue() {
		return mValue;
	}

	/**
	 * @param mValue the mValue to set
	 */
	public void setValue(float mValue) {
		this.mValue = mValue;
	}

	/**
	 * @return the mPlotName
	 */
	public String getPlotName() {
		return mPlotName;
	}

	/**
	 * @param mPlotName the mPlotName to set
	 */
	public void setPlotName(String mPlotName) {
		this.mPlotName = mPlotName;
	}

	/**
	 * @return the mColor
	 */
	public int getColor() {
		return mColor;
	}

	/**
	 * @param mColor the mColor to set
	 */
	public void setColor(int mColor) {
		this.mColor = mColor;
	};
}
