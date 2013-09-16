package com.example.healthcareapp.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class RadialProgressView extends View {

	private int mMaxValue = 1;
	private int mCurrentValue = 0;
	private int mProgressValue = 0;
	private Paint mViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mCenterColor = Color.WHITE;
	private int mBgColor = Color.parseColor("#20000000");
	private int mProgressColor = getContext().getResources().getColor(android.R.color.holo_blue_light);
	
	private RectF mRadialScoreRect;
	private int mRadius = 0;
	private Handler mProgressTimerHandler = new Handler();
	private OnRadialProgressListener mProgressCallback;
	private OnRadialAnimationListener mAnimationCallback;
	
	public interface OnRadialProgressListener {
		
		public void onRadialValueChanged(int value);
	}
	
	public interface OnRadialAnimationListener {
		
		public void onRadialAnimationStart();
		
		public void onRadialAnimationProgress();
		
		public void onRadialAnimationEnd();
	}
	
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
		if(mProgressValue > 0 && mProgressValue <= mMaxValue) {
			if(mProgressCallback != null)
				mProgressCallback.onRadialValueChanged(mProgressValue);
			float sweepAngle = ((mProgressValue * 360) / mMaxValue); 
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
	public void setCurrentValue(int mCurrentValue, boolean animate) {
		this.mProgressValue = this.mCurrentValue;
		this.mCurrentValue = mCurrentValue;
		//Log.i("Test", "mPrev " + mPrevValue + " Cur Val " + mCurrentValue);
		if(animate) {
			mProgressTimerHandler.postDelayed(animator, 0);
			if(mAnimationCallback != null)
				mAnimationCallback.onRadialAnimationStart();
		} else {
			this.mProgressValue = this.mCurrentValue;
			invalidate();
		}
	}	
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentValue() {
		return this.mCurrentValue;
	}
		
	/**
	 * @param mProgressCallback the mProgressCallback to set
	 */
	public void setProgressCallback(OnRadialProgressListener mProgressCallback) {
		this.mProgressCallback = mProgressCallback;
	}

	/**
	 * @param mAnimationCallback the mAnimationCallback to set
	 */
	public void setAnimationCallback(OnRadialAnimationListener mAnimationCallback) {
		this.mAnimationCallback = mAnimationCallback;
	}

	private Runnable animator = new Runnable() {
		@Override
		public void run() {
			if(mProgressValue != getCurrentValue()) {
				//Update value based on current value
				if(mProgressValue < getCurrentValue())
					mProgressValue++;
				else
					mProgressValue--;
				mProgressTimerHandler.postDelayed(this, 0);
				if(mAnimationCallback != null)
					mAnimationCallback.onRadialAnimationProgress();
				invalidate();
			} else {
				mProgressTimerHandler.removeCallbacks(this);
				if(mAnimationCallback != null)
					mAnimationCallback.onRadialAnimationEnd();
			}
			//Log.i("Test", "mPrev Runnable " + mPrevValue);			
		}
	};
}
