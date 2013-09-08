package com.example.healthcareapp.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LightTextView extends TextView {

	private final String FONT_NAME = "fonts/Roboto-Light.ttf";

	public LightTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
		setHorizontalFadingEdgeEnabled(false);
	}

	public LightTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
		setHorizontalFadingEdgeEnabled(false);
	}

	public LightTextView(Context context) {
		super(context);
		setTypeface(Typeface.createFromAsset(context.getAssets(),
				FONT_NAME));
		setHorizontalFadingEdgeEnabled(false);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused)
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}

	@Override
	public void onWindowFocusChanged(boolean focused) {
		if (focused)
			super.onWindowFocusChanged(focused);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
