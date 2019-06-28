package com.softomotion.catalogs.data.api.models.brochure;

import com.google.gson.annotations.SerializedName;

public class BrochureResponse {

	@SerializedName("response")
	private BrochureResponse brochureResponse;

	@SerializedName("brochure")
	private Brochure brochure;

	public void setBrochureResponse(BrochureResponse brochureResponse){
		this.brochureResponse = brochureResponse;
	}

	public BrochureResponse getBrochureResponse(){
		return brochureResponse;
	}

	public void setBrochure(Brochure brochure){
		this.brochure = brochure;
	}

	public Brochure getBrochure(){
		return brochure;
	}


	@Override
 	public String toString(){
		return 
			"BrochureResponse{" +
			"brochureResponse = '" + brochureResponse + '\'' +
			",brochure = '" + brochure + '\'' +
			"}";
		}
}