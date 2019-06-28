package com.softomotion.catalogs.core.map.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.core.base.BasePresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresItem;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresResponse;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.api.models.pins.PinsItem;
import com.softomotion.catalogs.data.api.models.pins.PinsResponse;
import com.softomotion.catalogs.data.database.DatabaseInstance;
import com.softomotion.catalogs.data.database.entities.Brochure;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.core.map.MapView;
import com.softomotion.catalogs.map.models.MapPin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenter  <V extends MapView> extends BasePresenter<V> implements MapPresenterInterface<V> {

    public MapPresenter(DataManager dataManager, Api api, DatabaseInstance db) {
        super(dataManager, api, db);
    }

    @Override
    public void loadCity(Integer city_id) {
        getApi().getCity(city_id, new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.isSuccessful()){
                    getmView().showCity(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                getmView().showError();
            }
        });
    }

    @Override
    public void loadPins(HashMap<String, Double> coordinates) {
        getApi().getCityPins(coordinates, getDataManager().getLocationCityId(), new Callback<PinsResponse>() {
            @Override
            public void onResponse(Call<PinsResponse> call, Response<PinsResponse> response) {
                if(response.isSuccessful()){
                    prepareMapPins(response.body().getPinsResponse().getPins());
                }
            }

            @Override
            public void onFailure(Call<PinsResponse> call, Throwable t) {
                getmView().showError();
            }
        });
    }

    @Override
    public void loadBrochures(Integer city_id, Integer[] brands_filters) {
        getApi().getBrandBrochures(city_id, brands_filters, new Callback<BrochuresResponse>() {
            @Override
            public void onResponse(Call<BrochuresResponse> call, Response<BrochuresResponse> response) {
                if(response.isSuccessful()){
                    responseBrochures(response.body().getBrochuresResponse().getBrochures());
                }
            }

            @Override
            public void onFailure(Call<BrochuresResponse> call, Throwable t) {
                getmView().showError();
            }
        });
    }


    private void responseBrochures(List<BrochuresItem> brochuresItems){
        getDb().getLikedBrochures(new DatabaseInstance.DatabaseListener<List<Integer>>() {
            @Override
            public void onFavouriteBrochuresLoaded(List<Integer> data) {
                getmView().showBrochures(brochuresItems, data);
            }
        });
    }


    @Override
    public void likeBrochure(BrochuresItem brochuresItem) {
        Brochure brochure = new Brochure();
        brochure.setBrochure_id(brochuresItem.getId());
        brochure.setBrochure_name(brochuresItem.getBrand().getName());
        brochure.setBrochure_image(brochuresItem.getPages().get(0).getImage().getMedium());
        getDb().likeBrochure(brochure);
    }

    @Override
    public void unLikeBrochure(Integer brochure_id) {
        getDb().unlikeBrochure(brochure_id);
    }

    private void prepareMapPins(List<PinsItem> pins){
        new AsyncTask<List<PinsItem>, Void, List<MapPin>>() {

            @Override
            protected List<MapPin> doInBackground(List<PinsItem>... lists) {
                List<MapPin> mapPins = new ArrayList<MapPin>();
                List<PinsItem> pins = lists[0];
                Log.d("API", "PINS");
                if(pins.size() != 0){
                    for (int pos = 0; pos < pins.size(); pos++) {
                        LatLng location = new LatLng(pins.get(pos).getCoordinates().getLatitude(), pins.get(pos).getCoordinates().getLongitude());
                        String title = pins.get(pos).getBrand().getName();
                        String snippet = pins.get(pos).getBrand().getName();
                        String image = AppConsts.STATIC_DOMAIN + pins.get(pos).getBrand().getLogo();
                        Integer brandId = pins.get(pos).getBrand().getId();
                        mapPins.add(new MapPin(location, title, snippet, image, brandId));
                    }
                }
                return mapPins;
            }

            @Override
            protected void onPostExecute(List<MapPin> mapPins) {
                super.onPostExecute(mapPins);
                getmView().showPins(mapPins);
            }
        }.execute(pins);
    }

}
