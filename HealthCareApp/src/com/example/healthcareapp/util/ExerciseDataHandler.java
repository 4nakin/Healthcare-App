package com.example.healthcareapp.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.healthcareapp.model.ExerciseItem;

public class ExerciseDataHandler extends DefaultHandler {
	
	private final String TAG_EXERCISE = "exercise";
	private final String TAG_NAME = "name";
	private final String TAG_DESCRIPTION = "description";
	private final String TAG_VIDEO_URL = "video-url";
	private final String TAG_AUDIO_URL = "audio-url";

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
		if(localName.equals(TAG_EXERCISE))
			mExerciseItem = new ExerciseItem();
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		elementOn = false;
		if(localName.equals(TAG_NAME))
			mExerciseItem.setExerciseName(elementValue);
		else if(localName.equals(TAG_DESCRIPTION))
			mExerciseItem.setExerciseDescription(elementValue);
		else if(localName.equals(TAG_VIDEO_URL))
			mExerciseItem.setExerciseVideoURL(elementValue);
		else if(localName.equals(TAG_AUDIO_URL))
			mExerciseItem.setExerciseAudioURL(elementValue);
		else if(localName.equals(TAG_EXERCISE))
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
