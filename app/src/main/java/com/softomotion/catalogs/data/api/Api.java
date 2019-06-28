package com.softomotion.catalogs.data.api;

import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.data.api.models.brochure.BrochureResponse;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresResponse;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.api.models.closest_city.ClosestCityResponse;
import com.softomotion.catalogs.data.api.models.pins.PinsResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {
    private static Api instance = null;
    private final ApiServices api;

    public static Api getInstance(){
        if(instance == null){
            instance = new Api();
        }
        return instance;
    }

    private Api(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConsts.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       this.api = retrofit.create(ApiServices.class);
    }

    public ApiServices getServices(){
        return this.api;
    }

    public void getClosestCity(HashMap<String, String> headers, Callback<ClosestCityResponse> responseCallback){
        Call<ClosestCityResponse> call = this.api.closest_city(headers);
        call.enqueue(responseCallback);
    }

    public void getBrochure(Integer brochure_id, Callback<BrochureResponse> responseCallBack){
        Call<BrochureResponse> call = this.api.getBrochure(brochure_id);
        call.enqueue(responseCallBack);
    }

    public void getCity(Integer city_id, Callback<List<City>> responseCallBack){
        Call<List<City>> call = this.api.city(city_id);
        call.enqueue(responseCallBack);
    }

    public void getCityPins(HashMap<String, Double> coordinates, Integer city_id, Callback<PinsResponse> responseCallback){
        Call<PinsResponse> call = this.api.pins(coordinates, city_id);
        call.enqueue(responseCallback);
    }

    public void getBrandBrochures(Integer city_id, Integer[] brands_filters, Callback<BrochuresResponse> responseCallback){
        Call<BrochuresResponse> call = this.api.getBrandBrochures(city_id, brands_filters);
        call.enqueue(responseCallback);
    }

    public void getAllCities(Callback<List<Cities>> responseCallback){
        Call<List<Cities>> call = this.api.cities();
        call.enqueue(responseCallback);
    }

    public void getBrochures(Integer city_id, Callback<BrochuresResponse> responseCallback){
        Call<BrochuresResponse> call = this.api.getBrochures(city_id);
        call.enqueue(responseCallback);
    }




}

