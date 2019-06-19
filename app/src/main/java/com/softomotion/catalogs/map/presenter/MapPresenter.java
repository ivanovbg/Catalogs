package com.softomotion.catalogs.map.presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.softomotion.catalogs.base.BasePresenter;
import com.softomotion.catalogs.data.api.Api;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.api.models.pins.PinsItem;
import com.softomotion.catalogs.data.prefs.DataManager;
import com.softomotion.catalogs.map.MapActivity;
import com.softomotion.catalogs.map.MapView;
import com.softomotion.catalogs.map.models.MapPin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenter  <V extends MapView> extends BasePresenter<V> implements MapPresenterInterface<V> {

    public MapPresenter(DataManager dataManager, Api api) {
        super(dataManager, api);
    }

    @Override
    public void getCity(Integer city_id) {
        getApi().getServices().city(city_id).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                Log.d("API", "TESTT");
                getmView().cityReady(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.d("API", t.toString());
            }
        });
    }

    @Override
    public void getPins(HashMap<String, Double> coordinates) {
        getApi().getServices().pins(coordinates, getDataManager().getCityId()).enqueue(new Callback<com.softomotion.catalogs.data.api.models.pins.Response>() {
            @Override
            public void onResponse(Call<com.softomotion.catalogs.data.api.models.pins.Response> call, Response<com.softomotion.catalogs.data.api.models.pins.Response> response) {
                prepareMapPins(response.body().getResponse().getPins());
            }

            @Override
            public void onFailure(Call<com.softomotion.catalogs.data.api.models.pins.Response> call, Throwable t) {
                Log.d("API", "TEST");
            }
        });
    }

    private void prepareMapPins(List<PinsItem> pins){
        List<MapPin> mapPins = new ArrayList<MapPin>();

        if(pins.size() != 0){
            for(int pos = 0; pos<pins.size(); pos++){
                    LatLng location = new LatLng(pins.get(pos).getCoordinates().getLatitude(), pins.get(pos).getCoordinates().getLongitude());
                    String title = pins.get(pos).getBrand().getName();
                    String snippet = pins.get(pos).getBrand().getName();
                    String image = "https://static.broshura.bg/img/" + pins.get(pos).getBrand().getLogo();
                    mapPins.add(new MapPin(location, title, snippet, image));
            }
        }
        getmView().pinsReady(mapPins);
    }

}
