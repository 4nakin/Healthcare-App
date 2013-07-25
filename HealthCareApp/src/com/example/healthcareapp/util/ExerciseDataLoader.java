package com.example.healthcareapp.util;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.ExerciseItem;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class ExerciseDataLoader extends AsyncTaskLoader<ArrayList<ExerciseItem>>{

	private Context mContext;
	
	public ExerciseDataLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<ExerciseItem> loadInBackground() {
		try {
            //URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            
            ExerciseDataHandler mResponseHandler=new ExerciseDataHandler();
            xmlreader.setContentHandler(mResponseHandler);
            //InputSource is=new InputSource(url.openStream());
            InputStream is = mContext.getResources().openRawResource(R.raw.exercise_list);
            xmlreader.parse(new InputSource(is));
            return mResponseHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ExerciseItem>();
        }
	}
}
