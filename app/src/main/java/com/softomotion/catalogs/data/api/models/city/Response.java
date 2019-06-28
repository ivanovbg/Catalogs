package com.softomotion.catalogs.data.api.models.city;


import com.google.gson.annotations.SerializedName;


public class Response{

	@SerializedName("image")
	private Object image;

	@SerializedName("meta_heading")
	private String metaHeading;

	@SerializedName("copied")
	private String copied;

	@SerializedName("description")
	private String description;

	@SerializedName("main")
	private String main;

	@SerializedName("meta_keywords")
	private Object metaKeywords;

	@SerializedName("banner2")
	private String banner2;

	@SerializedName("long")
	private String jsonMemberLong;

	@SerializedName("seo_link")
	private String seoLink;

	@SerializedName("banner1")
	private String banner1;

	@SerializedName("meta_description")
	private String metaDescription;

	@SerializedName("num_order")
	private String numOrder;

	@SerializedName("name")
	private String name;

	@SerializedName("is_region")
	private String isRegion;

	@SerializedName("id")
	private String id;

	@SerializedName("lat")
	private String lat;

	@SerializedName("name_en")
	private Object nameEn;

	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setMetaHeading(String metaHeading){
		this.metaHeading = metaHeading;
	}

	public String getMetaHeading(){
		return metaHeading;
	}

	public void setCopied(String copied){
		this.copied = copied;
	}

	public String getCopied(){
		return copied;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMain(String main){
		this.main = main;
	}

	public String getMain(){
		return main;
	}

	public void setMetaKeywords(Object metaKeywords){
		this.metaKeywords = metaKeywords;
	}

	public Object getMetaKeywords(){
		return metaKeywords;
	}

	public void setBanner2(String banner2){
		this.banner2 = banner2;
	}

	public String getBanner2(){
		return banner2;
	}

	public void setJsonMemberLong(String jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public String getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setSeoLink(String seoLink){
		this.seoLink = seoLink;
	}

	public String getSeoLink(){
		return seoLink;
	}

	public void setBanner1(String banner1){
		this.banner1 = banner1;
	}

	public String getBanner1(){
		return banner1;
	}

	public void setMetaDescription(String metaDescription){
		this.metaDescription = metaDescription;
	}

	public String getMetaDescription(){
		return metaDescription;
	}

	public void setNumOrder(String numOrder){
		this.numOrder = numOrder;
	}

	public String getNumOrder(){
		return numOrder;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIsRegion(String isRegion){
		this.isRegion = isRegion;
	}

	public String getIsRegion(){
		return isRegion;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setNameEn(Object nameEn){
		this.nameEn = nameEn;
	}

	public Object getNameEn(){
		return nameEn;
	}

	@Override
 	public String toString(){
		return 
			"BrochureResponse{" +
			"image = '" + image + '\'' + 
			",meta_heading = '" + metaHeading + '\'' + 
			",copied = '" + copied + '\'' + 
			",description = '" + description + '\'' + 
			",main = '" + main + '\'' + 
			",meta_keywords = '" + metaKeywords + '\'' + 
			",banner2 = '" + banner2 + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			",seo_link = '" + seoLink + '\'' + 
			",banner1 = '" + banner1 + '\'' + 
			",meta_description = '" + metaDescription + '\'' + 
			",num_order = '" + numOrder + '\'' + 
			",name = '" + name + '\'' + 
			",is_region = '" + isRegion + '\'' + 
			",id = '" + id + '\'' + 
			",lat = '" + lat + '\'' + 
			",name_en = '" + nameEn + '\'' + 
			"}";
		}
}