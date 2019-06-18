package com.softomotion.catalogs;

import android.app.Application;

import com.softomotion.catalogs.data.DataManager;
import com.softomotion.catalogs.data.SharedPrefsHelper;

public class Catalogs extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }


}