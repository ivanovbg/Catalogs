package com.softomotion.catalogs.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.softomotion.catalogs.R;

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

    public static AlertDialog.Builder showError(final Context context, final DialogInterface.OnClickListener positiveListener, final DialogInterface.OnClickListener negativeListener){
        androidx.appcompat.app.AlertDialog.Builder alertbox = new androidx.appcompat.app.AlertDialog.Builder(context);
        alertbox.setTitle(R.string.error_title);
        alertbox.setCancelable(false);
        alertbox.setMessage(R.string.error_message);
        alertbox.setNeutralButton(R.string.error_positive_button_text, positiveListener);
        alertbox.setNegativeButton(R.string.error_negative_button_text, negativeListener);
        return alertbox;
    }
}
