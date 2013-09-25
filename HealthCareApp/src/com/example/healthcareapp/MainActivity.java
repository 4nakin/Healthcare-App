package com.example.healthcareapp;

import com.example.healthcareapp.fragments.AboutFragment;
import com.example.healthcareapp.fragments.CalibrateSettingsFragment;
import com.example.healthcareapp.fragments.ChangePasswordFragment;
import com.example.healthcareapp.fragments.ExerciseDetailFragment;
import com.example.healthcareapp.fragments.ExerciseListFragment;
import com.example.healthcareapp.fragments.ProfileFragment;
import com.example.healthcareapp.fragments.SettingsFragment;
import com.example.healthcareapp.fragments.StatisticsFragment;
import com.example.healthcareapp.interfaces.OnExerciseListItemSelected;
import com.example.healthcareapp.interfaces.OnMainSlidingMenuItemSelected;
import com.example.healthcareapp.interfaces.OnSettingsListItemSelected;
import com.example.healthcareapp.model.ExerciseItem;
import com.example.healthcareapp.model.SettingsListItem;
import com.example.healthcareapp.slidingmenu.lib.SlidingMenu;
import com.example.healthcareapp.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.example.healthcareapp.util.AppPreferences;
import com.example.healthcareapp.views.MainSlidingMenuView;
import com.example.healthcareapp.views.UserGuidePopupWindow;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.healthcareapp.IOIyears.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends YouTubeBaseActivity implements
	OnExerciseListItemSelected, OnMainSlidingMenuItemSelected, OnSettingsListItemSelected {

	private boolean mTwoPane;
	private MainSlidingMenuView menuView;
	private SlidingMenu mAppMenu;
	private AppPreferences mPrefs;
	private int SELECTED_MENU_ITEM = -1;
	private UserGuidePopupWindow mGuideWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exerciseitem_list);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		getActionBar().setHomeButtonEnabled(true);
		if (findViewById(R.id.exerciseitem_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}		
		mGuideWindow = new UserGuidePopupWindow(this);
		mGuideWindow.setGuideView(UserGuidePopupWindow.MAIN_MENU_GUIDE);
		mPrefs = new AppPreferences(this);
		menuView = new MainSlidingMenuView(this);
		menuView.setOnMainSlidingMenuItemSelected(this);
		/** Configure the SlidingMenu **/
		mAppMenu = new SlidingMenu(this);
		mAppMenu.setMode(SlidingMenu.LEFT);
		mAppMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mAppMenu.setShadowWidthRes(R.dimen.shadow_width);
		mAppMenu.setShadowDrawable(R.drawable.shadow);
		mAppMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mAppMenu.setFadeDegree(0.35f);
		mAppMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		mAppMenu.setMenu(menuView.getView());
		/** Update the user info if there where changes made **/
		mAppMenu.setOnOpenListener(new OnOpenListener() {
			@Override
			public void onOpen() {
				menuView.updateUserInfo(MainActivity.this);
			}
		});
		/** Handle orientation changes here **/
		if(savedInstanceState == null) {
			/** Set the initial screen **/
			SELECTED_MENU_ITEM = OnMainSlidingMenuItemSelected.COURSES;
		} else {
			if(savedInstanceState.getBoolean("isMenuShowing"))
				mAppMenu.showMenu();
			/** Set the last viewed screen before the orientation changed **/
			SELECTED_MENU_ITEM = savedInstanceState.getInt("currentMenuSelection");
			menuView.setListSelection(SELECTED_MENU_ITEM);
		}
		changeMainContainerView(SELECTED_MENU_ITEM);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mAppMenu.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(!mPrefs.getMenuToggleGuideShown()) 
			findViewById(R.id.main_container).post(new Runnable() {
				public void run() {
					mGuideWindow.showAtLocation(findViewById(R.id.main_container), Gravity.FILL, 0, 0);
				}
			});		
	}
	
	/**
	 * Callback method from {@link ExerciseListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onListItemSelected(ExerciseItem data) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_ID, data.getExerciseID());
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_NAME, data.getExerciseName());
			arguments.putString(ExerciseDetailFragment.ARG_VIDEO_URL, data.getExerciseVideoURL());
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_DESCRIPTION, data.getExerciseDescription());
			arguments.putLong(ExerciseDetailFragment.ARG_SESSION_TIME, data.getExerciseSessionTime());
			ExerciseDetailFragment fragment = new ExerciseDetailFragment();
			fragment.setArguments(arguments);
			getFragmentManager().beginTransaction()
					.replace(R.id.exerciseitem_detail_container, fragment)
					.commit();
		} else {
			Intent detailIntent = new Intent(this,
					ExerciseDetailActivity.class);
			detailIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_ID, data.getExerciseID());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_NAME, data.getExerciseName());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_VIDEO_URL, data.getExerciseVideoURL());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_DESCRIPTION, data.getExerciseDescription());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_SESSION_TIME, data.getExerciseSessionTime());
			startActivity(detailIntent);
		}
	}

	@Override
	public void OnMenuItemSelected(int which) {
		SELECTED_MENU_ITEM = which;
		changeMainContainerView(SELECTED_MENU_ITEM);
		mAppMenu.toggle();
	}

	@Override
	public void onSettingsListItemClicked(int which, SettingsListItem data) {
		switch (which) {
		case OnSettingsListItemSelected.SETTINGS_CALIBRATE:
			if (mTwoPane) 
				getFragmentManager().beginTransaction()
					.replace(R.id.exerciseitem_detail_container, new CalibrateSettingsFragment())
					.commit();
			else
				showSettingsDetails(which, data);
			break;
		case OnSettingsListItemSelected.SETTINGS_DELETE:
			Toast.makeText(this, R.string.feature_na, Toast.LENGTH_LONG).show();
			break;
		case OnSettingsListItemSelected.SETTINGS_CHANGE_PASSWORD:
			if (mTwoPane) 
				getFragmentManager().beginTransaction()
					.replace(R.id.exerciseitem_detail_container, new ChangePasswordFragment())
					.commit();
			else
				showSettingsDetails(which, data);
			break;
		case OnSettingsListItemSelected.SETTINGS_HELP:
			Toast.makeText(this, R.string.feature_na, Toast.LENGTH_LONG).show();
			break;
		case OnSettingsListItemSelected.SETTINGS_LOGOUT:
			mPrefs.setLoggedOut();
			startActivity(new Intent(this, SplashActivity.class));
			finish();
			break;
		}		
	}
	
	private void showSettingsDetails(int which, SettingsListItem data) {
		Intent intent = new Intent(this, SettingsDetailsActivity.class);
		intent.putExtra(SettingsDetailsActivity.WHICH_SETTINGS, which);
		intent.putExtra(SettingsDetailsActivity.SETTINGS_NAME, data.getTitle());
		startActivity(intent);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("isMenuShowing", mAppMenu.isMenuShowing());
		outState.putInt("currentMenuSelection", SELECTED_MENU_ITEM);
	}
	
	/**
	 * Handle the screen containers in both phone and tablet mode
	 * @param which - The screen to be displayed
	 */
	private void changeMainContainerView(int which) {
		switch (which) {
		case OnMainSlidingMenuItemSelected.EDIT_PROFILE:
			getActionBar().setSubtitle(R.string.my_account);
			/** Hide the secondary container while on tablet mode**/
			if(findViewById(R.id.exerciseitem_detail_container) != null)
				findViewById(R.id.exerciseitem_detail_container).setVisibility(View.GONE);
			getFragmentManager().beginTransaction()
				.replace(R.id.main_container, new ProfileFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.ABOUT:
			getActionBar().setSubtitle(R.string.about);
			/** Hide the secondary container while on tablet mode**/
			if(findViewById(R.id.exerciseitem_detail_container) != null)
				findViewById(R.id.exerciseitem_detail_container).setVisibility(View.GONE);
			getFragmentManager().beginTransaction()
				.replace(R.id.main_container, new AboutFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.COURSES:
			getActionBar().setSubtitle(R.string.my_courses);
			/** Show the secondary container while on tablet mode**/
			if(findViewById(R.id.exerciseitem_detail_container) != null)
				findViewById(R.id.exerciseitem_detail_container).setVisibility(View.VISIBLE);
			getFragmentManager().beginTransaction()
				.replace(R.id.main_container, new ExerciseListFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.SETTINGS:
			getActionBar().setSubtitle(R.string.settings);
			/** Show the secondary container while on tablet mode**/
			if(findViewById(R.id.exerciseitem_detail_container) != null)
				findViewById(R.id.exerciseitem_detail_container).setVisibility(View.VISIBLE);
			getFragmentManager().beginTransaction()
				.replace(R.id.main_container, new SettingsFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.STATISTICS:
			getActionBar().setSubtitle(R.string.statistics);
			/** Hide the secondary container while on tablet mode**/
			if(findViewById(R.id.exerciseitem_detail_container) != null)
				findViewById(R.id.exerciseitem_detail_container).setVisibility(View.GONE);
			getFragmentManager().beginTransaction()
				.replace(R.id.main_container, new StatisticsFragment())
				.commit();
			break;
		}
		/** Clean the secondary fragment in case of tablet mode **/
		if(findViewById(R.id.exerciseitem_detail_container) != null)
			((FrameLayout) findViewById(R.id.exerciseitem_detail_container)).removeAllViews();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	       if(mAppMenu.isMenuShowing())
	    	   mAppMenu.toggle();
	       else
	    	   finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onStartSessionItemSelected(ExerciseItem data) {
		Intent sessionIntent = new Intent(this, ExerciseSessionActivity.class); 
		sessionIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_ID, data.getExerciseID());
		sessionIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_NAME, data.getExerciseName());
		sessionIntent.putExtra(ExerciseDetailFragment.ARG_VIDEO_URL, data.getExerciseVideoURL());
		sessionIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_DESCRIPTION, data.getExerciseDescription());
		sessionIntent.putExtra(ExerciseDetailFragment.ARG_SESSION_TIME, data.getExerciseSessionTime());
		startActivity(sessionIntent);
	}
}
