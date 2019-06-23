package com.softomotion.catalogs.data.api.models.brochure;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("brochure")
	private Brochure brochure;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
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
			"Response{" + 
			"response = '" + response + '\'' + 
			",brochure = '" + brochure + '\'' +
			"}";
		}
}