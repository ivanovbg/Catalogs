package com.softomotion.catalogs.data.api.models.closest_city;

import com.google.gson.annotations.SerializedName;

public class ClosestCityResponse {

	@SerializedName("response")
	private ClosestCityResponse closestCityResponse;

	@SerializedName("city")
	private City city;

	public void setClosestCityResponse(ClosestCityResponse closestCityResponse){
		this.closestCityResponse = closestCityResponse;
	}

	public ClosestCityResponse getClosestCityResponse(){
		return closestCityResponse;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	@Override
 	public String toString(){
		return
			"Cities{" +
			"closestCityResponse = '" + closestCityResponse + '\'' +
			",city = '" + city + '\'' +
			"}";
		}
}
