package com.softomotion.catalogs.data.api.models.brochures;

import com.google.gson.annotations.SerializedName;

public class BrandsItem{

	@SerializedName("filename")
	private String filename;

	@SerializedName("name")
	private String name;

	@SerializedName("logo")
	private String logo;

	@SerializedName("id")
	private String id;

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLogo(String logo){
		this.logo = logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"BrandsItem{" + 
			"filename = '" + filename + '\'' + 
			",name = '" + name + '\'' + 
			",logo = '" + logo + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}