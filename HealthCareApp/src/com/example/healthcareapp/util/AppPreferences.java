package com.example.healthcareapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

	private SharedPreferences mAppPrefs;

	public AppPreferences(Context context) {
		mAppPrefs = context.getSharedPreferences("101YearsUserPreferences", 0); // 0 is private
	}
	
	public void setUsername(String userName) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putString("101YearsUsername", userName);
		edit.commit();
	}
	
	public String getUsername() {
		return mAppPrefs.getString("101YearsUsername", null);
	}
	
	public void setFirstname(String firstName) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putString("101YearsFirstName", firstName);
		edit.commit();
	}
	
	public String getFirstname() {
		return mAppPrefs.getString("101YearsFirstName", null);
	}
	
	public void setLastname(String lastName) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putString("101YearsLastName", lastName);
		edit.commit();		
	}
	
	public String getLastname() {
		return mAppPrefs.getString("101YearsLastName", null);
	}

	public void setEmail(String email) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putString("101YearsEmail", email);
		edit.commit();
	}
	
	public String getEmail() {
		return mAppPrefs.getString("101YearsEmail", null);
	}
	
	public void setPassword(String password) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putString("101YearsPassword", password);
		edit.commit();
	}
	
	public String getPassword() {
		return mAppPrefs.getString("101YearsPassword", null);
	}
	
	public void setGender(int gender) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putInt("101YearsGender", gender);
		edit.commit();
	}
	
	public int getGender() {
		return mAppPrefs.getInt("101YearsGender", -1);
	}
	
	public void setDateOfBirth(int year, int month, int day) {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putInt("101YearsDOBYear", year);
		edit.putInt("101YearsDOBMonth", month);
		edit.putInt("101YearsDOBDay", day);
		edit.commit();
	}
	
	public int getDOBYear() {
		return mAppPrefs.getInt("101YearsDOBYear", -1);
	}
	
	public int getDOBMonth() {
		return mAppPrefs.getInt("101YearsDOBMonth", -1);
	}
	
	public int getDOBDay() {
		return mAppPrefs.getInt("101YearsDOBDay", -1);
	}
	
	public void setLoggedIn() {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putBoolean("101YearsLoggedIn", true);
		edit.commit();
	}
	
	public void setLoggedOut() {
		SharedPreferences.Editor edit = mAppPrefs.edit();
		edit.putBoolean("101YearsLoggedIn", false);
		edit.commit();
	}
	
	public boolean isUserLoggedIn() {
		return mAppPrefs.getBoolean("101YearsLoggedIn", false);
	}
}
