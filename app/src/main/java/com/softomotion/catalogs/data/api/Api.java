package com.softomotion.catalogs.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softomotion.catalogs.data.api.models.closest_city.Response;

import java.util.HashMap;

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
                .baseUrl("https://api.broshura.bg/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       this.api = retrofit.create(ApiServices.class);
    }

    public ApiServices getServices(){
        return this.api;
    }

    public void getCurrentCity(Callback<Response> responseCallback, HashMap<String, String> headers){
        Call<Response> call = this.api.closest_city(headers);
        call.enqueue(responseCallback);
    }
    //
    //    public void getCities(Callback<List<Cities>> responseCallback){
    //        Call<List<Cities>> call = this.api.cities();
    //        call.enqueue(responseCallback);
    //    }

}

