package com.example.healthcareapp.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.healthcareapp.model.CommentsItem;

public class CommentsDataHandler extends DefaultHandler {

	private final String ATTR_COMMENT_COUNT = "comment-count";
	private final String ATTR_LIKE_COUNT = "like-count";
	private final String ATTR_UNLIKE_COUNT = "dislike-count";
	private final String NODE_RESPONSE = "response";
	private final String NODE_COMMENT = "comment";
	private final String NODE_USERNAME = "username";
	private final String NODE_LIKE = "like";
	private final String NODE_COMMENT_TEXT = "comment-text";
	
	private String elementValue = null;
	private Boolean elementOn = false;
	private ArrayList<CommentsItem> mData = new ArrayList<CommentsItem>();
	private CommentsItem data;
	private String mCommentCount, mLikeCount, mUnlikeCount;
		
	/**
	 * @return the mData
	 */
	public ArrayList<CommentsItem> getData() {
		return mData;
	}

	/**
	 * @return the mCommentCount
	 */
	public String getCommentCount() {
		return mCommentCount;
	}

	/**
	 * @return the mLikeCount
	 */
	public String getLikeCount() {
		return mLikeCount;
	}

	/**
	 * @return the mUnlikeCount
	 */
	public String getDislikeCount() {
		return mUnlikeCount;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		elementOn = true;
		if(localName.equals(NODE_RESPONSE)) {
			mCommentCount = attributes.getValue(ATTR_COMMENT_COUNT);
			mLikeCount = attributes.getValue(ATTR_LIKE_COUNT);
			mUnlikeCount = attributes.getValue(ATTR_UNLIKE_COUNT);
		} else if(localName.equals(NODE_COMMENT)) {
			data = new CommentsItem();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		elementOn = false;
		if(localName.equals(NODE_USERNAME))
			data.setUsername(elementValue);
		else if(localName.equals(NODE_LIKE))
			data.setLiked(Integer.valueOf(elementValue));
		else if(localName.equals(NODE_COMMENT_TEXT))
			data.setComment(elementValue);
		else if(localName.equals(NODE_COMMENT))
			mData.add(data);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (elementOn) {
			elementValue = new String(ch, start, length);
			elementOn = false;
		}
	}
}
