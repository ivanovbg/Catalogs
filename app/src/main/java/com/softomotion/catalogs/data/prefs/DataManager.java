package com.softomotion.catalogs.data.prefs;

import android.util.Log;

public class DataManager {

    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void putCoords(String latitude, String longitude) {
        mSharedPrefsHelper.putCoords(latitude, longitude);
    }

    public String[] getCoords(){
        return mSharedPrefsHelper.getCoords();
    }

    public void putCityId(Integer city_id){
        mSharedPrefsHelper.putCityId(city_id);
    }

    public  Integer getCityId(){
        return mSharedPrefsHelper.getCityId();
    }
}

