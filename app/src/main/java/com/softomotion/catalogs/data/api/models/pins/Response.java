package com.softomotion.catalogs.data.api.models.pins;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("search")
	private int search;

	@SerializedName("pins")
	private List<PinsItem> pins;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setSearch(int search){
		this.search = search;
	}

	public int getSearch(){
		return search;
	}

	public void setPins(List<PinsItem> pins){
		this.pins = pins;
	}

	public List<PinsItem> getPins(){
		return pins;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			",search = '" + search + '\'' + 
			",pins = '" + pins + '\'' + 
			"}";
		}
}