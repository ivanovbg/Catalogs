package com.softomotion.catalogs.data.api.models.brochure;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Brand{

	@SerializedName("name")
	private String name;


	@SerializedName("logo")
	private String logo;

	@SerializedName("id")
	private int id;

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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Brand{" + 
			"name = '" + name + '\'' +
			",logo = '" + logo + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}