package com.softomotion.catalogs.data.api.models.brochures;

import com.google.gson.annotations.SerializedName;


public class Image{

	@SerializedName("large")
	private String large;

	@SerializedName("thumb")
	private String thumb;

	@SerializedName("medium")
	private String medium;

	public void setLarge(String large){
		this.large = large;
	}

	public String getLarge(){
		return large;
	}

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"large = '" + large + '\'' + 
			",thumb = '" + thumb + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}
}