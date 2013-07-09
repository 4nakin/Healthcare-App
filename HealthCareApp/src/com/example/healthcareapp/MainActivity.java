package com.example.healthcareapp;

import com.example.healthcareapp.fragments.AboutFragment;
import com.example.healthcareapp.fragments.ExerciseDetailFragment;
import com.example.healthcareapp.fragments.ExerciseListFragment;
import com.example.healthcareapp.fragments.ProfileFragment;
import com.example.healthcareapp.fragments.SettingsFragment;
import com.example.healthcareapp.fragments.StatisticsFragment;
import com.example.healthcareapp.interfaces.OnExerciseListItemSelected;
import com.example.healthcareapp.interfaces.OnMainSlidingMenuItemSelected;
import com.example.healthcareapp.model.ExerciseItem;
import com.example.healthcareapp.slidingmenu.lib.SlidingMenu;
import com.example.healthcareapp.views.MainSlidingMenuView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements
	OnExerciseListItemSelected, OnMainSlidingMenuItemSelected {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private MainSlidingMenuView menuView;
	private SlidingMenu mAppMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exerciseitem_list);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_action_bar_bg));
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setTitle(R.string.my_courses);
		if (findViewById(R.id.exerciseitem_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ExerciseListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.exerciseitem_list))
					.setActivateOnItemClick(true);
		}
		
		menuView = new MainSlidingMenuView(this);
		menuView.setOnMainSlidingMenuItemSelected(this);
		
		// configure the SlidingMenu
		mAppMenu = new SlidingMenu(this);
		mAppMenu.setMode(SlidingMenu.LEFT);
		mAppMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mAppMenu.setShadowWidthRes(R.dimen.shadow_width);
		mAppMenu.setShadowDrawable(R.drawable.shadow);
		mAppMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mAppMenu.setFadeDegree(0.35f);
		mAppMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		mAppMenu.setMenu(menuView.getView());
		
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_container, new ExerciseListFragment())
			.commit();
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
	
	/**
	 * Callback method from {@link ExerciseListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onListItemSelected(ExerciseItem data) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_NAME, data.getExerciseName());
			arguments.putString(ExerciseDetailFragment.ARG_ITEM_URL, data.getExerciseVideoURL());
			arguments.putBoolean(ExerciseDetailFragment.ARG_IS_AUDIO, data.isAudio());
			ExerciseDetailFragment fragment = new ExerciseDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.exerciseitem_detail_container, fragment)
					.commit();
		} else {
			Intent detailIntent = new Intent(this,
					ExerciseDetailActivity.class);
			detailIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_NAME, data.getExerciseName());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_ITEM_URL, data.getExerciseVideoURL());
			detailIntent.putExtra(ExerciseDetailFragment.ARG_IS_AUDIO, data.isAudio());
			startActivity(detailIntent);
		}
	}

	@Override
	public void OnMenuItemSelected(int which) {
		switch (which) {
		case OnMainSlidingMenuItemSelected.EDIT_PROFILE:
			getActionBar().setTitle(R.string.my_account);
			getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_container, new ProfileFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.ABOUT:
			getActionBar().setTitle(R.string.about);
			getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_container, new AboutFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.COURSES:
			getActionBar().setTitle(R.string.my_courses);
			getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_container, new ExerciseListFragment())
				.commit();
			break;
		case OnMainSlidingMenuItemSelected.SETTINGS:
			getActionBar().setTitle(R.string.settings);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_container, new SettingsFragment())
			.commit();
			break;
		case OnMainSlidingMenuItemSelected.STATISTICS:
			getActionBar().setTitle(R.string.statistics);
			getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_container, new StatisticsFragment())
				.commit();
			break;
		}
		mAppMenu.toggle();
	}
}
