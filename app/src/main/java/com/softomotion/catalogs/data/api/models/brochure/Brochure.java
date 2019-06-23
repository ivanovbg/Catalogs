package com.softomotion.catalogs.data.api.models.brochure;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Brochure{

	@SerializedName("pages")
	private List<PagesItem> pages;

	@SerializedName("id")
	private int id;

	@SerializedName("validity")
	private Validity validity;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("brochure_name")
	private String brochureName;

	@SerializedName("is_liked")
	private boolean isLiked;

	public void setPages(List<PagesItem> pages){
		this.pages = pages;
	}

	public List<PagesItem> getPages(){
		return pages;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setValidity(Validity validity){
		this.validity = validity;
	}

	public Validity getValidity(){
		return validity;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
	}

	public void setBrochureName(String brochureName){
		this.brochureName = brochureName;
	}

	public String getBrochureName(){
		return brochureName;
	}

	public void setIsLiked(boolean isLiked){
		this.isLiked = isLiked;
	}

	public boolean isIsLiked(){
		return isLiked;
	}

	@Override
 	public String toString(){
		return 
			"Brochure{" + 
			"pages = '" + pages + '\'' + 
			",id = '" + id + '\'' + 
			",validity = '" + validity + '\'' +
			",brand = '" + brand + '\'' + 
			",brochure_name = '" + brochureName + '\'' + 
			",is_liked = '" + isLiked + '\'' + 
			"}";
		}
}