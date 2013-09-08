package com.example.healthcareapp.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class LightEditText extends EditText {

	private final String FONT_NAME = "fonts/Roboto-Light.ttf";

	public LightEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
	}

	public LightEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
	}

	public LightEditText(Context context) {
		super(context);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
	}
}
