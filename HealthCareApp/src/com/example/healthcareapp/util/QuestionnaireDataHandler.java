package com.example.healthcareapp.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.healthcareapp.model.QuestionnaireItem;

public class QuestionnaireDataHandler extends DefaultHandler {

	private final String TAG_QUESTION = "question";
	private final String ATTR_NAME = "name";
	
	@SuppressWarnings("unused")
	private String elementValue = null;
	private Boolean elementOn = false;
	private ArrayList<QuestionnaireItem> mData = new ArrayList<QuestionnaireItem>();
	
	public ArrayList<QuestionnaireItem> getData() {
		return mData;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		elementOn = true;
		if(localName.equals(TAG_QUESTION)) {
			mData.add(new QuestionnaireItem(attributes.getValue(ATTR_NAME)));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		elementOn = false;
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
