package com.softomotion.catalogs.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.softomotion.catalogs.core.AppConsts;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {
        private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(AppConsts.MY_PREFS, MODE_PRIVATE);
    }

    public void putUserCityId(Integer city_id){
        mSharedPreferences.edit().putInt("user_city_id", city_id).apply();
    }

    public Integer getUserCityId(){
        return mSharedPreferences.getInt("user_city_id", 0);
    }

    public void putLocationCityId(Integer city_id){
        mSharedPreferences.edit().putInt("location_city_id", city_id).apply();
    }

    public Integer getLocationCityId(){
        return mSharedPreferences.getInt("location_city_id", 0);
    }


    public void putCityId(Integer city_id){
        mSharedPreferences.edit().putInt("city_id", city_id).apply();
    }

    public Integer getCityId(){
        return mSharedPreferences.getInt("city_id", 0);
    }


}
