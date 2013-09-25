package com.example.healthcareapp.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

public class AinimationUtils {

	public Animation blinkAnim(int duration, int count) {
		Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
	    animation.setDuration(duration); // duration - half a second
	    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
	    animation.setRepeatCount(count); // Repeat animation infinitely
	    animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
	    return animation;
	}
}
