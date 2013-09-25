package com.example.healthcareapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "_101YearsScore.db";
	private static final int DB_VERSION = 1;
	
	public final String DB_TABLE_SCORE = "_statistics";
	public final String DB_TABLE_SCORE_ROW_ID = "_statisticsRowID";
	public final String DB_TABLE_SCORE_EXERCISE_ID = "_statisticsExerciseID";
	public final String DB_TABLE_SCORE_EXERCISE_NAME = "_statisticsExerciseName";
	public final String DB_TABLE_SCORE_USER_SCORE = "_statisticsScore";
	public final String DB_TABLE_SCORE_SESSION_TIME = "_statisticsSessionTime";
	public final String DB_TABLE_SCORE_SESSION_YEAR = "_statisticsSessionYear";
	public final String DB_TABLE_SCORE_SESSION_MONTH = "_statisticsSessionMonth";
	public final String DB_TABLE_SCORE_SESSION_DAY = "_statisticsSessionDay";
	public final String DB_TABLE_SCORE_SESSION_WEEK = "_statisticsSessionWeek";
	
	public final String DB_CREATE_TABLE_SCORE_DETAILS = "create table  " + DB_TABLE_SCORE +
			"(" + DB_TABLE_SCORE_ROW_ID + " integer primary key autoincrement, " +
			DB_TABLE_SCORE_EXERCISE_ID + " text not null, " +
			DB_TABLE_SCORE_EXERCISE_NAME + " text not null, " +
			DB_TABLE_SCORE_USER_SCORE + " INTEGER DEFAULT 0, " +
			DB_TABLE_SCORE_SESSION_TIME + " INTEGER DEFAULT 0," +
			DB_TABLE_SCORE_SESSION_YEAR + " INTEGER DEFAULT 0, " +
			DB_TABLE_SCORE_SESSION_MONTH + " INTEGER DEFAULT 0, " +
			DB_TABLE_SCORE_SESSION_DAY + " INTEGER DEFAULT 0," +
			DB_TABLE_SCORE_SESSION_WEEK + " INTEGER DEFAULT 0);";
	
	public final String[] allScoreColumns = new String[]{DB_TABLE_SCORE_ROW_ID,
		DB_TABLE_SCORE_EXERCISE_ID,
		DB_TABLE_SCORE_EXERCISE_NAME,
		DB_TABLE_SCORE_USER_SCORE,
		DB_TABLE_SCORE_SESSION_TIME,
		DB_TABLE_SCORE_SESSION_YEAR,
		DB_TABLE_SCORE_SESSION_MONTH,
		DB_TABLE_SCORE_SESSION_DAY,
		DB_TABLE_SCORE_SESSION_WEEK};

	public ScoreDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_TABLE_SCORE_DETAILS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_SCORE);
		onCreate(db);		
	}
}
