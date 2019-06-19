package com.softomotion.catalogs.main.presenter;


import com.softomotion.catalogs.base.BasePresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.main.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPressenter <V extends MainView> extends BasePresenter<V> implements MainPressenterInterface<V> {

    public MainPressenter(DataManager dataManager, Api api){
        super(dataManager, api);
    }


    @Override
    public void getCities(){
        getApi().getServices().cities().enqueue(new Callback<List<Cities>>() {
            @Override
            public void onResponse(Call<List<Cities>> call, Response<List<Cities>> response) {
                List<Cities> cities = response.body();
                getmView().citiesReady(cities);
            }

            @Override
            public void onFailure(Call<List<Cities>> call, Throwable t) {

            }
        });
    }

}
