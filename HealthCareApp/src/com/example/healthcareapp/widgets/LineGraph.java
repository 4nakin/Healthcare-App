package com.example.healthcareapp.widgets;

import java.util.LinkedHashMap;

import com.example.healthcareapp.model.LineGraphData;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LineGraph extends View {
	
	private int graphAxesColor = getResources().getColor(android.R.color.darker_gray);	
	private int graphPadding;
	
	private float MAX_VALUE = 0;
	private int graphYAxesLineSpacingFactor = 10;	
	private int PLOT_LINES_INTERVAL_FACTOR = 5;
	private Paint mBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Rect mTextRect = new Rect();	
	private int graphWidth;	
	private int graphHeight;	
	private int graphPlotWidth;	
	private int graphPlotHeight;
	private int graphOriginX;
	private int graphOriginY;	
	private int graphMaxWidth;
	
	// These two variables keep track of the X and Y coordinate of the finger
	// when it first
	// touches the screen
	private float startX = 0f;
	private float startY = 0f;
		
	// These two variables keep track of the amount we need to translate the
	// canvas along the X
	// and the Y coordinate
	private float translateX = 0f;
	private float translateY = 0f;
		
	// These two variables keep track of the amount we translated the X and Y
	// coordinates, the last time we
	// panned.
	private float previousTranslateX = 0f;
	private float previousTranslateY = 0f;
	
	private LinkedHashMap <String, LineGraphData> mChartData = new LinkedHashMap <String, LineGraphData>();

	public LineGraph(Context context) {
		super(context);
		initDefaultView();
	}
	
	public LineGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		initDefaultView();
	}
	
	public LineGraph(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initDefaultView();
	}
	
	private void initDefaultView() {
		mBarPaint.setTextSize(12.0f * getResources().getDisplayMetrics().density);
		mBarPaint.setTextAlign(Align.CENTER);
		mBarPaint.setStrokeWidth(4.0f);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.translate(translateX, 0);
		graphMaxWidth = graphOriginX+graphPadding;
		int oldX = graphOriginY;	
		for(LineGraphData data : mChartData.values()) {
			mBarPaint.setStyle(Style.FILL);
			mBarPaint.setColor(data.getColor());
			canvas.drawCircle(graphMaxWidth, getLineHeight(data.getValue()), 8.0f, mBarPaint);
			if(graphMaxWidth != (graphOriginX + graphPadding))
				canvas.drawLine(graphMaxWidth-graphPadding, oldX, graphMaxWidth, getLineHeight(data.getValue()), mBarPaint);
			mBarPaint.setColor(graphAxesColor);
			mBarPaint.getTextBounds(data.getPlotName(), 0, data.getPlotName().length(), mTextRect);
			canvas.drawText(data.getPlotName(), graphMaxWidth + 4, 
					graphOriginY + 15 * getResources().getDisplayMetrics().density, mBarPaint);
			graphMaxWidth += graphPadding;
			oldX = getLineHeight(data.getValue());
		}
		graphMaxWidth = graphOriginX + graphPadding;
		mBarPaint.setColor(Color.WHITE);
		for(LineGraphData data : mChartData.values()) {
			canvas.drawCircle(graphMaxWidth, getLineHeight(data.getValue()), 5.0f, mBarPaint);
			graphMaxWidth += graphPadding;
		}
		canvas.restore();
		/** Draw the Axes Lines**/
		mBarPaint.setColor(graphAxesColor);
		mBarPaint.setStyle(Style.STROKE);
		mBarPaint.setStrokeWidth(2.0f);
		//canvas.drawLine(graphOriginX, getPaddingTop(), graphOriginX, graphOriginY, mBarPaint);
		canvas.drawLine(graphOriginX, graphOriginY, graphWidth - getPaddingRight(), graphOriginY, mBarPaint);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		graphWidth = w;
		graphHeight = h;
		graphOriginX = getPaddingLeft();
		graphOriginY = graphHeight - (h/10) - getPaddingBottom();
		graphPlotWidth = graphWidth - graphOriginX - getPaddingRight() - (w/20);
		graphPadding = (int) ((graphPlotWidth/8) * getResources().getDisplayMetrics().density);
		graphPlotHeight = graphOriginY - getPaddingTop() - (h/20);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
			// All fingers went up, so let’s save the value of translateX
			// and translateY into previousTranslateX and
			// previousTranslate
			previousTranslateX = translateX;
			previousTranslateY = translateY;
			break;

		case MotionEvent.ACTION_DOWN:
			// We assign the current X and Y coordinate of the finger to
			// startX and startY minus the previously translated
			// amount for each coordinates This works even when we are
			// translating the first time because the initial
			// values for these two variables is zero.
			startX = event.getX() - previousTranslateX;
			startY = event.getY() - previousTranslateY;
			break;

		case MotionEvent.ACTION_MOVE:
			//Log.i("Test", "Translate : " + startX + " Max " + graphMaxWidth);
			if(startX >= graphMaxWidth/5
				&& startX <= (graphMaxWidth - (graphMaxWidth/5))) {
				translateX = event.getX() - startX;
				translateY = event.getY() - startY;
			}
			break;
		}
		invalidate();
		return true;
	}
		
	/** 
	 * Get height based on max value ratio
	 * @param value
	 * @return
	 */
	private int getLineHeight(float value) {
		int size = graphOriginY - (int) ((value/MAX_VALUE) * graphPlotHeight);
		return size;
	}
	
	public void addPlotData(String id, LineGraphData data) {
		mChartData.put(id, data);
		if(MAX_VALUE < data.getValue()) {
			int size = (String.valueOf((int) data.getValue())).length();
			if(size!=0)
				PLOT_LINES_INTERVAL_FACTOR = (size - 1) * graphYAxesLineSpacingFactor;
			MAX_VALUE = (float) (Math.ceil(data.getValue() / PLOT_LINES_INTERVAL_FACTOR) * PLOT_LINES_INTERVAL_FACTOR);
		}
		invalidate();
	}
		
	public void removePlotData(String id) {
		mChartData.remove(id);
		invalidate();
	}
	
	public LineGraphData getPlotData(String id) {
		return mChartData.get(id);
	}
}
