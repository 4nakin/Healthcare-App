package com.example.healthcareapp.views;

import com.healthcareapp.IOIyears.R;
import com.example.healthcareapp.model.QuestionnaireDetailsItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionnaireView {

	private QuestionnaireDetailsItem mData;
	private Context mContext;
	private LayoutInflater mInflater;
	private View convertView;
	private TextView mQuestionText, mPositiveBtn, mNegativeBtn, mAnswerText;
	private ViewFlipper mFooterFlipper;
	private EditText mAnswer;
		
	public QuestionnaireView(Context context, QuestionnaireDetailsItem data) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mData = data;
		initUI(mData);
	}
	
	private void initUI(final QuestionnaireDetailsItem data) {
		convertView = mInflater.inflate(R.layout.layout_questionnaire_details_list, null);
		mQuestionText = (TextView) convertView.findViewById(R.id.adapter_questionnaire_text);
		mFooterFlipper = (ViewFlipper) convertView.findViewById(R.id.adapter_questionnaire_list_footer_layout);
		mPositiveBtn = (TextView) convertView.findViewById(R.id.adapter_questionnaire_positive_btn);
		mNegativeBtn = (TextView) convertView.findViewById(R.id.adapter_questionnaire_negative_btn);
		mAnswer = (EditText) convertView.findViewById(R.id.adapter_questionnaire_answer);
		mAnswerText = (TextView) convertView.findViewById(R.id.adapter_questionnaire_answer_text);
		
		mQuestionText.setText(data.getQuestion());
		getAnswer(data);
		
		if(QuestionnaireDetailsItem.EDITABLE == data.getType()) {
			mAnswer.setVisibility(View.VISIBLE);
			mPositiveBtn.setText(R.string.save);
			mNegativeBtn.setText(R.string.clear);	
		}
						
		mPositiveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(data.getType() == 1) {
					if(mAnswer.getText().toString().trim().length() > 0) 
						data.setAnswerText(mAnswer.getText().toString().trim());
					else
						Toast.makeText(mContext, R.string.msg_questionnaire_answer, Toast.LENGTH_LONG).show();
				} else 
					data.setAnswerText("1");
				getAnswer(data);
			}
		});

		mNegativeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(data.getType() == 1) 
					mAnswer.getEditableText().clear();
				else {
					data.setAnswerText("0");
					getAnswer(data);
				}
			}
		});
	}
	
	public View getBaseView() {
		return convertView;
	}
	
	private void getAnswer(QuestionnaireDetailsItem data) {
		if(data.getAnswerText() != null) {
			switch(data.getType()) {
			case QuestionnaireDetailsItem.NORMAL:
				if(data.getAnswerText().equals("0")) {
					mAnswerText.setText(mContext.getText(R.string.you_answered) 
							+ " " + mContext.getText(R.string.no));
					mFooterFlipper.showNext();
				} else if(data.getAnswerText().equals("1")) {
					mAnswerText.setText(mContext.getText(R.string.you_answered) 
							+ " " + mContext.getText(R.string.yes));
					mFooterFlipper.showNext();
				}
				break;
			case QuestionnaireDetailsItem.EDITABLE:
				if(!data.getAnswerText().equals("NA")) {
					mAnswerText.setText(mContext.getText(R.string.you_answered) 
							+ " " + data.getAnswerText());
					mFooterFlipper.showNext();
				}
				break;
			}
		}
	}
}
