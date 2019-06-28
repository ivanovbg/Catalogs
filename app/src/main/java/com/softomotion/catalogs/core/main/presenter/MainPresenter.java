package com.softomotion.catalogs.core.main.presenter;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.main.MainView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter <V extends MainView> extends BasePresenter<V> implements MainPresenterInterface<V> {
    public MainPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void loadCities(){
        getApi().getAllCities(new Callback<List<Cities>>() {
            @Override
            public void onResponse(Call<List<Cities>> call, Response<List<Cities>> response) {
                if(response.isSuccessful()){
                    getmView().showCities(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Cities>> call, Throwable t) {

            }
        });
    }
}
