package com.softomotion.catalogs.data.prefs;

import android.util.Log;

public class DataManager {

    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void setUserCityId(Integer city_id) {
        mSharedPrefsHelper.putUserCityId(city_id);
    }

    public Integer getUserCityId() {
        return mSharedPrefsHelper.getUserCityId();
    }

    public void setLocationCityId(Integer city_id) {
        mSharedPrefsHelper.putLocationCityId(city_id);
    }

    public Integer getLocationCityId() {
        return mSharedPrefsHelper.getLocationCityId();
    }


    public void putCityId(Integer city_id) {
        mSharedPrefsHelper.putCityId(city_id);
    }

    public Integer getCityId() {
        return mSharedPrefsHelper.getCityId();
    }
}

