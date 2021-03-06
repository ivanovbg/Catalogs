package com.softomotion.catalogs.data.api;

import com.softomotion.catalogs.core.AppConsts;
import com.softomotion.catalogs.data.api.models.brochure.BrochureResponse;
import com.softomotion.catalogs.data.api.models.brochures.BrochuresResponse;
import com.softomotion.catalogs.data.api.models.cities.Cities;
import com.softomotion.catalogs.data.api.models.city.City;
import com.softomotion.catalogs.data.api.models.closest_city.ClosestCityResponse;
import com.softomotion.catalogs.data.api.models.pins.PinsResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServices {

    @GET(AppConsts.API_CITY_PATH)
    Call<ClosestCityResponse> closest_city(@HeaderMap Map<String, String> headers);

    @GET(AppConsts.API_CITIES_PATH)
    Call <List<Cities>> cities();

    @GET(AppConsts.API_CITIES_PATH)
    Call <List<City>> city(@Query("id") Integer city_id);

    @GET(AppConsts.API_PINS_PATH)
    Call<PinsResponse> pins(@QueryMap Map<String, Double> coordinates, @Query("city_id") Integer city_id);

    @GET(AppConsts.API_BROCHURES_PATH)
    Call<BrochuresResponse> getBrandBrochures(@Query("city_id") Integer city_id, @Query("brand_filters[]") Integer brands[]);

    @GET(AppConsts.API_BROCHURES_PATH)
    Call<BrochuresResponse> getBrochures(@Query("city_id") Integer city_id);

    @GET(AppConsts.API_BROCHURE_PATH)
    Call<BrochureResponse> getBrochure(@Query("brochure_id") Integer brochure_id);
}
