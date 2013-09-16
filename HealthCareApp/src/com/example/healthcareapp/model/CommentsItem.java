package com.example.healthcareapp.model;

public class CommentsItem {

	private boolean isLiked;
	private String mComment;
	private String mUsername;
	
	public CommentsItem() { }

	/**
	 * @param isLiked
	 * @param mComment
	 * @param mUsername
	 */
	public CommentsItem(boolean isLiked, String mComment, String mUsername) {
		super();
		this.isLiked = isLiked;
		this.mComment = mComment;
		this.mUsername = mUsername;
	}

	/**
	 * @return the isLiked
	 */
	public boolean isLiked() {
		return isLiked;
	}

	/**
	 * @param isLiked the isLiked to set
	 */
	public void setLiked(int isLiked) {
		if(isLiked == 0)
			this.isLiked = false;
		else
			this.isLiked = true;
	}

	/**
	 * @return the mComment
	 */
	public String getComment() {
		return mComment;
	}

	/**
	 * @param mComment the mComment to set
	 */
	public void setComment(String mComment) {
		this.mComment = mComment;
	}

	/**
	 * @return the mTimeStamp
	 */
	public String getUsername() {
		return mUsername;
	}

	/**
	 * @param username the mTimeStamp to set
	 */
	public void setUsername(String username) {
		this.mUsername = username;
	}
}
