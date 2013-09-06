package com.example.healthcareapp.util;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.model.QuestionnaireDetailsItem;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class QuestionnaireDetialsLoader extends AsyncTaskLoader<ArrayList<QuestionnaireDetailsItem>>{

	private Context mContext;
	
	public QuestionnaireDetialsLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<QuestionnaireDetailsItem> loadInBackground() {
		try {
            //URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            
            QuestionnaireDetailsDataHandler mResponseHandler=new QuestionnaireDetailsDataHandler();
            xmlreader.setContentHandler(mResponseHandler);
            //InputSource is=new InputSource(url.openStream());
            InputStream is = mContext.getResources().openRawResource(R.raw.questionnaire_details_list);
            xmlreader.parse(new InputSource(is));
            return mResponseHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<QuestionnaireDetailsItem>();
        }
	
	}
}
