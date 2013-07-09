package com.example.healthcareapp.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RadialProgressView extends View {

	private int mMaxValue = 101;
	private int mCurrentValue = 23;
	private Paint mViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mCenterColor = Color.WHITE;
	private int mBgColor = Color.parseColor("#20000000");
	private int mProgressColor = getContext().getResources().getColor(android.R.color.holo_blue_bright);
	private RectF mRadialScoreRect;
	
	private int mRadius = 0;
	
	public RadialProgressView(Context context) {
		super(context);
	}
	
	public RadialProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	public RadialProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mViewPaint.setColor(mBgColor);
		canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius, mViewPaint);
		
		mViewPaint.setColor(mProgressColor);
		if(mCurrentValue <= mMaxValue) {
			float sweepAngle = ((mCurrentValue * 360) / mMaxValue); 
			canvas.drawArc(mRadialScoreRect, 270, sweepAngle, true, mViewPaint);
		}
		
		mViewPaint.setColor(mCenterColor);
		canvas.drawCircle(getWidth()/2, getHeight()/2, (float) (mRadius * .9), mViewPaint);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(w>h) 
			mRadius = (h/2) - (getPaddingTop() + getPaddingBottom());
		else
			mRadius = (w/2) - (getPaddingLeft() + getPaddingRight());
		//Init the draw arc Rect object
		int left = (getWidth()/2) - (int) mRadius + getPaddingLeft();
		int right = (getWidth()/2) + (int) mRadius - getPaddingRight();
		int top = (getHeight()/2) - (int) mRadius + getPaddingTop();
		int bottom = (getHeight()/2) + (int) mRadius - getPaddingBottom();
		Rect rect = new Rect(left, top, right, bottom);
		mRadialScoreRect = new RectF(rect); 
	}

	/**
	 * @param mMaxValue the mMaxValue to set
	 */
	public void setMaxValue(int mMaxValue) {
		this.mMaxValue = mMaxValue;
		invalidate();
	}

	/**
	 * @param mCurrentValue the mCurrentValue to set
	 */
	public void setCurrentValue(int mCurrentValue) {
		this.mCurrentValue = mCurrentValue;
		invalidate();
	}	
}
