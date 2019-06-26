package com.softomotion.catalogs.core.splash.presenter;

import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.splash.SplashView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.closest_city.Response;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

public class SplashPresenter <V extends SplashView> extends BasePresenter<V> implements SplashPresenterInterface<V> {

    public SplashPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void findCity(HashMap<String, String> coordinates) {
        getApi().getServices().closest_city(coordinates).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful() && response.body().getResponse() != null && response.body().getResponse().getCity() != null){
                    Integer city_id = response.body().getResponse().getCity().getId();
                    getDataManager().putCityId(city_id);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
