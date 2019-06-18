package com.softomotion.catalogs.data.api.models.closest_city;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("city")
	private City city;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
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
			"response = '" + response + '\'' +
			",city = '" + city + '\'' +
			"}";
		}
}
