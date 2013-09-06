package com.example.healthcareapp.util;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.model.QuestionnaireItem;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class QuestionnaireDataLoader extends AsyncTaskLoader<ArrayList<QuestionnaireItem>>{

	private Context mContext;
	
	public QuestionnaireDataLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<QuestionnaireItem> loadInBackground() {
		try {
            //URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            
            QuestionnaireDataHandler mResponseHandler=new QuestionnaireDataHandler();
            xmlreader.setContentHandler(mResponseHandler);
            //InputSource is=new InputSource(url.openStream());
            InputStream is = mContext.getResources().openRawResource(R.raw.questionnaire_list);
            xmlreader.parse(new InputSource(is));
            return mResponseHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<QuestionnaireItem>();
        }
	}
}
