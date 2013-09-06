package com.example.healthcareapp.util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.healthcareapp.model.QuestionnaireDetailsItem;

public class QuestionnaireDetailsDataHandler extends DefaultHandler {

	private final String NODE_QUESTION = "question";
	private final String ATTR_ID = "id";
	private final String NODE_DETAILS = "question-detials";
	private final String NODE_TYPE = "question-type";
	private final String NODE_ANSWER = "question-answer";
	
	private String elementValue = null;
	private Boolean elementOn = false;
	private ArrayList<QuestionnaireDetailsItem> mData = new ArrayList<QuestionnaireDetailsItem>();
	private QuestionnaireDetailsItem data;
	
	public ArrayList<QuestionnaireDetailsItem> getData() {
		return mData;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		elementOn = true;
		if(localName.equals(NODE_QUESTION)) {
			data = new QuestionnaireDetailsItem(
					Integer.valueOf(attributes.getValue(ATTR_ID)));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		elementOn = false;
		if(localName.equals(NODE_DETAILS))
			data.setQuestion(elementValue);
		else if(localName.equals(NODE_TYPE))
			data.setType(Integer.valueOf(elementValue));
		else if(localName.equals(NODE_ANSWER))
			data.setAnswerText(elementValue);
		else if(localName.equals(NODE_QUESTION))
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
