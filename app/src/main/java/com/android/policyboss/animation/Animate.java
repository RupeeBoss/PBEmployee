package com.android.policyboss.animation;

import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Rajeev Ranjan on 29/05/2017.
 */

public class Animate {

    public static ScaleAnimation SlideUpAnimation(final View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new LinearOutSlowInInterpolator());
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(scaleAnimation);

        return scaleAnimation;
    }

    public static ScaleAnimation SlideDownAnimation(final View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new LinearOutSlowInInterpolator());
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(scaleAnimation);

        return scaleAnimation;
    }

    public static TranslateAnimation translateUpAnimation(final View view, float fromx, float tox, float fromy, float toy) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromx, tox, fromy, toy);
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(translateAnimation);
        return translateAnimation;
    }
}
