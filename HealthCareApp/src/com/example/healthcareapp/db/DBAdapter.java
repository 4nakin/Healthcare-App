package com.example.healthcareapp.db;

import org.joda.time.LocalDate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {

	private ScoreDatabaseHelper mScoreDBHelper;
	private SQLiteDatabase mDatabase;
	
	public DBAdapter(Context context) {
		mScoreDBHelper = new ScoreDatabaseHelper(context);
	}
	
	public void openScoreDB() throws SQLException {
		mDatabase = mScoreDBHelper.getWritableDatabase();
	}
	
	public void closeScoreDB() {
		mScoreDBHelper.close();
	}
	
	public long insertNewScore(String exerciseID, String exerciseName, int score, long time) {
		LocalDate insertionDay = new LocalDate();
		ContentValues scoreTableValues = new ContentValues();
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_EXERCISE_ID, exerciseID);
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_EXERCISE_NAME, exerciseName);
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_USER_SCORE, score);
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_SESSION_TIME, time);
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_SESSION_YEAR, insertionDay.getYear());
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_SESSION_MONTH, insertionDay.getMonthOfYear());
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_SESSION_DAY, insertionDay.getDayOfMonth());
		scoreTableValues.put(mScoreDBHelper.DB_TABLE_SCORE_SESSION_WEEK, insertionDay.getWeekOfWeekyear());
		return mDatabase.insert(mScoreDBHelper.DB_TABLE_SCORE, null, scoreTableValues);
	}
	
	public Cursor getScoreByMonth(int year, int month) {
		return mDatabase.query(mScoreDBHelper.DB_TABLE_SCORE, 
				mScoreDBHelper.allScoreColumns, 
				mScoreDBHelper.DB_TABLE_SCORE_SESSION_MONTH + "='" + String.valueOf(month) + "' AND " +
						mScoreDBHelper.DB_TABLE_SCORE_SESSION_YEAR + "='" + String.valueOf(year) + "'", null, null, null, null);
	}
	
	public Cursor getScoreByWeek(int year, int week) {
		return mDatabase.query(mScoreDBHelper.DB_TABLE_SCORE, 
				mScoreDBHelper.allScoreColumns, 
				mScoreDBHelper.DB_TABLE_SCORE_SESSION_WEEK + "='" + String.valueOf(week) + "' AND " +
						mScoreDBHelper.DB_TABLE_SCORE_SESSION_YEAR + "='" + String.valueOf(year) + "'", null, null, null, null);
	}
	
	/*public Cursor getAllTypes() {
		return mSQLDB.query(mDefinations.DATABASE_TABLE_LEVEL_TYPE_DETAILS, new String[] {
				mDefinations.DATABASE_TABLE_LEVEL_TYPE_DETAILS_ROWID, // 0
				mDefinations.DATABASE_TABLE_LEVEL_TYPE_DETAILS_TYPE}, // 1
				null, null, null, null, null);
	}
	
	dbAdapter.open();
			Cursor mCursor = dbAdapter.getAllTypes();
			if (mCursor != null) {
				if (mCursor.moveToFirst()) {
					do {
						data.add(mCursor.getString(1).trim());
					} while (mCursor.moveToNext());
				}
			}
			dbAdapter.close();
	
	public void deleteAllBookmarks() {
		mSQLDB.delete(mDefinations.DATABASE_TABLE_BOOKMARKS, null, null);
	}
	
	public boolean deleteBookmark(long rowId) {
		return mSQLDB.delete(mDefinations.DATABASE_TABLE_BOOKMARKS, mDefinations.DATABASE_TABLE_BOOKMARKS_ROWID + "=" + rowId, null) > 0;
	}*/
}
