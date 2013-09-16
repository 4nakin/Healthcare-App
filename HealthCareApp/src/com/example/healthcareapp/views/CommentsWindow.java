package com.example.healthcareapp.views;

import java.util.ArrayList;

import com.example.healthcareapp.adapter.CommentsListAdapter;
import com.example.healthcareapp.interfaces.OnCommentsLoadedListener;
import com.example.healthcareapp.model.CommentsItem;
import com.example.healthcareapp.threads.CommentsUserTask;
import com.healthcareapp.IOIyears.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class CommentsWindow extends PopupWindow 
	implements OnCommentsLoadedListener {

	private LayoutInflater mInflater;
	private View mViewHolder;
	private TextView mComments, mLikes, mDislikes;
	private EditText mCommentsEditText;
	private ListView mCommentsList;
	private CommentsListAdapter mAdapter;
 	private Context mContext;
	
	public CommentsWindow(Context context) {
		super(context);		
		mAdapter = new CommentsListAdapter(context);
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
		mViewHolder = mInflater.inflate(R.layout.layout_comments_popup, null);
		mComments = (TextView) mViewHolder.findViewById(R.id.comments_popup_header_comments_count_text);
		mLikes = (TextView) mViewHolder.findViewById(R.id.comments_popup_header_likes_count_text);
		mDislikes = (TextView) mViewHolder.findViewById(R.id.comments_popup_header_dislikes_count_text);
		mCommentsList = (ListView) mViewHolder.findViewById(R.id.comments_popup_list);
		mCommentsEditText = (EditText) mViewHolder.findViewById(R.id.comments_user_input);
		mCommentsList.setAdapter(mAdapter);
		
		setContentView(mViewHolder);
		setHeight(LayoutParams.MATCH_PARENT);
		setWidth(LayoutParams.MATCH_PARENT);
		setBackgroundDrawable(null);
		setFocusable(true);
				
		mViewHolder.findViewById(R.id.comments_popup_close_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		mViewHolder.findViewById(R.id.comments_popup_post_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mCommentsEditText.getText().toString().trim().length() > 0)
					mCommentsEditText.getEditableText().clear();
				else
					Toast.makeText(mContext, R.string.msg_enter_comment, Toast.LENGTH_LONG).show();		
			}
		});
	}
				
	@Override
	public void dismiss() {
		super.dismiss();
		mAdapter.setListData(new ArrayList<CommentsItem>());
		mComments.setText("0 comments");
		mLikes.setText("0");
		mDislikes.setText("0");
	}
	
	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		new CommentsUserTask(mContext, this).execute(new String[] {""});
	}

	@Override
	public void onCommentsLoaded(ArrayList<CommentsItem> data,
			String commentCount, String likeCount, String dislikeCount) {
		mAdapter.setListData(data);
		mComments.setText(commentCount + " comments");
		mLikes.setText(likeCount);
		mDislikes.setText(dislikeCount);
	}
}
