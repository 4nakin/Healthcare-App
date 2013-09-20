package com.example.healthcareapp.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.healthcareapp.model.ExerciseItem;

public class ExerciseDataHandler extends DefaultHandler {
	
	private final String ATTR_ID = "id";
	private final String NODE_EXERCISE = "exercise";
	private final String NODE_NAME = "name";
	private final String NODE_DESCRIPTION = "description";
	private final String NODE_VIDEO_URL = "video-url";
	private final String NODE_AUDIO_URL = "audio-url";
	private final String NODE_COMMENTS_URL = "comments-url";
	private final String NODE_DURATION = "duration";

	private String elementValue = null;
	private Boolean elementOn = false;
	private ArrayList<ExerciseItem> mData = new ArrayList<ExerciseItem>();
	private ExerciseItem mExerciseItem;
		
	/**
	 * @return the mData
	 */
	public ArrayList<ExerciseItem> getData() {
		return mData;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		elementOn = true;
		if(localName.equals(NODE_EXERCISE))
			mExerciseItem = new ExerciseItem(attributes.getValue(ATTR_ID));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		elementOn = false;
		if(localName.equals(NODE_NAME))
			mExerciseItem.setExerciseName(elementValue);
		else if(localName.equals(NODE_DESCRIPTION))
			mExerciseItem.setExerciseDescription(elementValue);
		else if(localName.equals(NODE_VIDEO_URL))
			mExerciseItem.setExerciseVideoURL(elementValue);
		else if(localName.equals(NODE_AUDIO_URL))
			mExerciseItem.setExerciseAudioURL(elementValue);
		else if(localName.equals(NODE_COMMENTS_URL))
			mExerciseItem.setExerciseCommentURL(elementValue);
		else if(localName.equals(NODE_DURATION))
			mExerciseItem.setExerciseSessionTime(elementValue);
		else if(localName.equals(NODE_EXERCISE))
			mData.add(mExerciseItem);
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
