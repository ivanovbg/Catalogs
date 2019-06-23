package com.softomotion.catalogs;

import android.app.Application;

import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.data.prefs.SharedPrefsHelper;
import com.softomotion.catalogs.data.api.Api;

public class Catalogs extends Application {

    DataManager dataManager;
    Api api;
    DatabaseInstance db;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
        api = Api.getInstance();
        db = DatabaseInstance.getInstance(getApplicationContext());
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public Api getApiManager() { return api;}

    public DatabaseInstance getDatabaseInstance(){
        return db;
    }


}