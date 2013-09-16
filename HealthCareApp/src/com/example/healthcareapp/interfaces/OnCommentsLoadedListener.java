package com.example.healthcareapp.interfaces;

import java.util.ArrayList;

import com.example.healthcareapp.model.CommentsItem;

public interface OnCommentsLoadedListener {
	public void onCommentsLoaded(ArrayList<CommentsItem> data, String commentCount, String likeCount, String dislikeCount);
}
