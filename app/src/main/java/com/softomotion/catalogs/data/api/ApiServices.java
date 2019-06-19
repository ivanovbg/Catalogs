package com.softomotion.catalogs.data.api;

import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.api.models.closest_city.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServices {

    @GET("/closest_city")
    Call<Response> closest_city(@HeaderMap Map<String, String> headers);

    @GET("/cities")
    Call <List<Cities>> cities();

    @GET("/cities")
    Call <List<City>> city(@Query("id") Integer city_id);

    @GET("/get_map_pins")
    Call<com.softomotion.catalogs.data.api.models.pins.Response> pins(@QueryMap Map<String, Double> coordinates, @Query("city_id") Integer city_id);

}
