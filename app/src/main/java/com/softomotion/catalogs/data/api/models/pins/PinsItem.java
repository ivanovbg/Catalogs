package com.softomotion.catalogs.data.api.models.pins;


import com.google.gson.annotations.SerializedName;


public class PinsItem{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("city_name")
	private Object cityName;

	@SerializedName("coordinates")
	private Coordinates coordinates;

	@SerializedName("pin_images")
	private PinImages pinImages;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("is_liked")
	private boolean isLiked;

	@SerializedName("city_id")
	private int cityId;

	public void setShopId(int shopId){
		this.shopId = shopId;
	}

	public int getShopId(){
		return shopId;
	}

	public void setCityName(Object cityName){
		this.cityName = cityName;
	}

	public Object getCityName(){
		return cityName;
	}

	public void setCoordinates(Coordinates coordinates){
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates(){
		return coordinates;
	}

	public void setPinImages(PinImages pinImages){
		this.pinImages = pinImages;
	}

	public PinImages getPinImages(){
		return pinImages;
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

	public void setCityId(int cityId){
		this.cityId = cityId;
	}

	public int getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"PinsItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",city_name = '" + cityName + '\'' + 
			",coordinates = '" + coordinates + '\'' + 
			",pin_images = '" + pinImages + '\'' + 
			",brand = '" + brand + '\'' + 
			",is_liked = '" + isLiked + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}