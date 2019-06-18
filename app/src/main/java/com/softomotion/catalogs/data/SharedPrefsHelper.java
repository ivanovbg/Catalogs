package com.softomotion.catalogs.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {
    public static final String MY_PREFS = "MY_PREFS";

    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void putCoords(String latitude, String longitude){
         mSharedPreferences.edit().putString("latitude", latitude).putString("longitude", longitude).apply();
    }

    public String[] getCoords(){
        String[] coords = {
                mSharedPreferences.getString("latitude", "-34"),
                mSharedPreferences.getString("longitude", "151")
        };


        return coords;
    }
}
