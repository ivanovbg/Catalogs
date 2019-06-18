package com.softomotion.catalogs.data.api;

import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.api.models.closest_city.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface ApiServices {

    @GET("/closest_city")
    Call<Response> closest_city(@HeaderMap Map<String, String> headers);

    @GET("/cities")
    Call <List<Cities>> cities();


}
