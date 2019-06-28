package com.softomotion.catalogs.data.api.models.pins;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PinsResponse {

	@SerializedName("response")
	private PinsResponse pinsResponse;

	@SerializedName("search")
	private int search;

	@SerializedName("pins")
	private List<PinsItem> pins;

	public void setPinsResponse(PinsResponse pinsResponse){
		this.pinsResponse = pinsResponse;
	}

	public PinsResponse getPinsResponse(){
		return pinsResponse;
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
			"BrochureResponse{" +
			"pinsResponse = '" + pinsResponse + '\'' +
			",search = '" + search + '\'' + 
			",pins = '" + pins + '\'' + 
			"}";
		}
}