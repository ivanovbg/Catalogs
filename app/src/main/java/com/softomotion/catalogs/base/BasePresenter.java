package com.softomotion.catalogs.base;

import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.prefs.DataManager;

public class BasePresenter<V extends View> implements Presenter<V> {

    private V mView;
    private DataManager dataManager;
    private Api api;

    public BasePresenter(DataManager dataManager, Api api){
        this.dataManager = dataManager;
        this.api = api;
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


}
