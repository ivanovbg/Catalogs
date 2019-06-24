package com.softomotion.catalogs.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class CommonUtils {

    public static void showSnackBar(){
         Log.d("API", "API TEST");
    }


    public static void animateView(final View view, final int toVisibility, float toAlpha, int duration) {
        boolean show = toVisibility == View.VISIBLE;
        if (show) {
            view.setAlpha(0);
        }
        view.setVisibility(View.VISIBLE);
        view.animate()
                .setDuration(duration)
                .alpha(show ? toAlpha : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(toVisibility);
                    }
                });
    }

    public static String convertDate(long time){
        return new java.text.SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date(time*1000));
    }
}
