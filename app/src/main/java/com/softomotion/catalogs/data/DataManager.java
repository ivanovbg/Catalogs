package com.softomotion.catalogs.data;

import android.util.Log;

import java.util.List;

public class DataManager {

    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void putCoords(String latitude, String longitude) {
        Log.d("TEEST", "PUT COORDS");
        mSharedPrefsHelper.putCoords(latitude, longitude);
    }

    public String[] getCoords(){
        Log.d("TEEST", "GET COORDS");
        Log.d("TEEST", mSharedPrefsHelper.getCoords().toString());
        return mSharedPrefsHelper.getCoords();
    }
}

