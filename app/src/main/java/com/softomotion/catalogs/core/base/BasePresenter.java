package com.softomotion.catalogs.core.base;

import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

public class BasePresenter<V extends View> implements Presenter<V> {

    private V mView;
    private DataManager dataManager;
    private Api api;
    private DatabaseInstance db;

    public BasePresenter(DataManager dataManager, Api api, DatabaseInstance db){
        this.dataManager = dataManager;
        this.api = api;
        this.db = db;
    }


    @Override
    public void onAttach(V mView) {
        this.mView = mView;
    }

    public V getmView(){
        return mView;
    }

    public DataManager getDataManager(){
        return dataManager;
    }

    public Api getApi(){
        return api;
    }

    public DatabaseInstance getDb() {
        return db;
    }
}
