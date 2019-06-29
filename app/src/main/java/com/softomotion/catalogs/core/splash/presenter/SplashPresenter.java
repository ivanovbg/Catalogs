package com.softomotion.catalogs.core.splash.presenter;

import android.location.Location;

import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.core.splash.SplashView;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.closest_city.ClosestCityResponse;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.prefs.DataManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter <V extends SplashView> extends BasePresenter<V> implements SplashPresenterInterface<V> {

    public SplashPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void findCity(Location location) {
        HashMap<String, String> coordinates = new HashMap<String, String>();
        coordinates.put("latitude", String.valueOf(location.getLatitude()));
        coordinates.put("longitude", String.valueOf(location.getLongitude()));

        getApi().getClosestCity(coordinates, new Callback<ClosestCityResponse>() {
            @Override
            public void onResponse(Call<ClosestCityResponse> call, Response<ClosestCityResponse> response) {
                Integer city_id = AppConsts.DEFAULT_CITY_ID;
                if(response.isSuccessful() && response.body().getClosestCityResponse() != null && response.body().getClosestCityResponse().getCity() != null){
                    city_id = response.body().getClosestCityResponse().getCity().getId();
                }

                getDataManager().setLocationCityId(city_id);
                if(getDataManager().getUserCityId() == 0){
                    getDataManager().setUserCityId(city_id);
                }
            }

            @Override
            public void onFailure(Call<ClosestCityResponse> call, Throwable t) {
                getDataManager().setLocationCityId(AppConsts.DEFAULT_CITY_ID);
                if(getDataManager().getUserCityId() == 0){
                    getDataManager().setUserCityId(AppConsts.DEFAULT_CITY_ID);
                }
            }
        });
    }
}
