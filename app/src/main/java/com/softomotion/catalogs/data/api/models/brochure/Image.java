package com.softomotion.catalogs.data.api.models.brochure;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("large_width")
	private String largeWidth;

	@SerializedName("large")
	private String large;

	@SerializedName("thumb")
	private String thumb;

	@SerializedName("medium_height")
	private String mediumHeight;

	@SerializedName("medium_width")
	private String mediumWidth;

	@SerializedName("medium")
	private String medium;

	@SerializedName("large_height")
	private String largeHeight;

	public void setLargeWidth(String largeWidth){
		this.largeWidth = largeWidth;
	}

	public String getLargeWidth(){
		return largeWidth;
	}

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

	public void setMediumHeight(String mediumHeight){
		this.mediumHeight = mediumHeight;
	}

	public String getMediumHeight(){
		return mediumHeight;
	}

	public void setMediumWidth(String mediumWidth){
		this.mediumWidth = mediumWidth;
	}

	public String getMediumWidth(){
		return mediumWidth;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}

	public void setLargeHeight(String largeHeight){
		this.largeHeight = largeHeight;
	}

	public String getLargeHeight(){
		return largeHeight;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"large_width = '" + largeWidth + '\'' + 
			",large = '" + large + '\'' + 
			",thumb = '" + thumb + '\'' + 
			",medium_height = '" + mediumHeight + '\'' + 
			",medium_width = '" + mediumWidth + '\'' + 
			",medium = '" + medium + '\'' + 
			",large_height = '" + largeHeight + '\'' + 
			"}";
		}
}