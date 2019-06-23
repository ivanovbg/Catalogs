package com.softomotion.catalogs.data.api.models.brochures;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BrochuresItem{

	@SerializedName("duration")
	private String duration;

	@SerializedName("distance_to_shop")
	private String distanceToShop;

	@SerializedName("pages")
	private List<PagesItem> pages;

	@SerializedName("heading")
	private String heading;

	@SerializedName("id")
	private int id;

	@SerializedName("validity")
	private Validity validity;

	@SerializedName("category")
	private Category category;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("is_liked")
	private boolean isLiked;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setDistanceToShop(String distanceToShop){
		this.distanceToShop = distanceToShop;
	}

	public String getDistanceToShop(){
		return distanceToShop;
	}

	public void setPages(List<PagesItem> pages){
		this.pages = pages;
	}

	public List<PagesItem> getPages(){
		return pages;
	}

	public void setHeading(String heading){
		this.heading = heading;
	}

	public String getHeading(){
		return heading;
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

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
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
			"BrochuresItem{" + 
			"duration = '" + duration + '\'' + 
			",distance_to_shop = '" + distanceToShop + '\'' + 
			",pages = '" + pages + '\'' + 
			",heading = '" + heading + '\'' + 
			",id = '" + id + '\'' + 
			",validity = '" + validity + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			",is_liked = '" + isLiked + '\'' + 
			"}";
		}
}