package com.example.healthcareapp.widgets;

import java.util.LinkedHashMap;

import com.example.healthcareapp.R;
import com.example.healthcareapp.model.LineGraphData;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class LineGraph extends View {
	
	private int graphAxesColor = getResources().getColor(android.R.color.darker_gray);	
	private int graphPadding = (int) (10 * getResources().getDisplayMetrics().density);
	
	private float MAX_VALUE = 0;
	private int graphYAxesLineSpacingFactor = 10;	
	private int PLOT_LINES_INTERVAL_FACTOR = 5;
	private Paint mBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int graphWidth;	
	private int graphHeight;	
	private int graphPlotWidth;	
	private int graphPlotHeight;
	private int graphOriginX;
	private int graphOriginY;	
	private int graphBarWidth;	
	
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
		mBarPaint.setTextSize(12.0f);
		mBarPaint.setTextAlign(Align.CENTER);
		mBarPaint.setStrokeWidth(4.0f);
		addPlotData("1", new LineGraphData("1", 20.0f, "Test 1", getResources().getColor(R.color.pink_dark)));
		addPlotData("2", new LineGraphData("2", 10.0f, "Test 2", getResources().getColor(R.color.pink_dark)));
		addPlotData("3", new LineGraphData("3", 25.0f, "Test 3", getResources().getColor(R.color.pink_dark)));
		addPlotData("4", new LineGraphData("4", 30.0f, "Test 4", getResources().getColor(R.color.pink_dark)));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		graphBarWidth = getBarWidth(mChartData.size());
		int startX = graphOriginX + graphBarWidth;
		int oldY = graphOriginY;		
		for(LineGraphData data : mChartData.values()) {
			mBarPaint.setStyle(Style.FILL);
			mBarPaint.setColor(data.getColor());
			canvas.drawCircle(startX, getBarHeight(data.getValue()), 8.0f, mBarPaint);
			canvas.drawLine(startX-graphBarWidth, oldY, startX, getBarHeight(data.getValue()), mBarPaint);
			startX += graphBarWidth;
			oldY = getBarHeight(data.getValue());
		}
		startX = graphOriginX + graphBarWidth;
		mBarPaint.setColor(Color.WHITE);
		for(LineGraphData data : mChartData.values()) {
			canvas.drawCircle(startX, getBarHeight(data.getValue()), 5.0f, mBarPaint);
			startX += graphBarWidth;
		}
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
		graphPlotHeight = graphOriginY - getPaddingTop() - (h/20);
	}
	
	private int getBarWidth(int dataSize) {
		if(dataSize > 1) {
			//(drawing space width/number of bars) - (gaps within bars left and right)
			return (graphPlotWidth/dataSize) - (graphPadding * 2);
		} else
			return (graphWidth/10);
	}
	
	/** 
	 * Get height based on max value ratio
	 * @param value
	 * @return
	 */
	private int getBarHeight(float value) {
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
