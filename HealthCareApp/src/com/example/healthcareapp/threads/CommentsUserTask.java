package com.example.healthcareapp.threads;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.example.healthcareapp.interfaces.OnCommentsLoadedListener;
import com.example.healthcareapp.model.CommentsItem;
import com.example.healthcareapp.util.CommentsDataHandler;
import com.healthcareapp.IOIyears.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class CommentsUserTask extends AsyncTask<String, Void, ArrayList<CommentsItem>> {

	private ProgressDialog mProgressDialog;
	private Context mContext;
	private String mCommentCount = "0 comments", mLikeCount = "0", mDislikeCount = "0";
	private OnCommentsLoadedListener mCallback;
	
	public CommentsUserTask(Context context, OnCommentsLoadedListener callback) {
		mCallback = callback;
		mContext = context;
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage("Loading comments...");
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);		
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog.show();
	}
	
	@Override
	protected ArrayList<CommentsItem> doInBackground(String... params) {
		try {
            //URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            
            CommentsDataHandler mResponseHandler=new CommentsDataHandler();
            xmlreader.setContentHandler(mResponseHandler);
            //InputSource is=new InputSource(url.openStream());
            InputStream is = mContext.getResources().openRawResource(R.raw.comments_list);
            xmlreader.parse(new InputSource(is));
            mCommentCount = mResponseHandler.getCommentCount();
            mLikeCount = mResponseHandler.getLikeCount();
            mDislikeCount = mResponseHandler.getDislikeCount();
            return mResponseHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CommentsItem>();
        }
	}

	@Override
	protected void onPostExecute(ArrayList<CommentsItem> result) {
		super.onPostExecute(result);
		mProgressDialog.dismiss();
		mCallback.onCommentsLoaded(result, mCommentCount, mLikeCount, mDislikeCount);
	}
}
