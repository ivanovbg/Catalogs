package com.softomotion.catalogs.data.api.models.brochures;

import com.google.gson.annotations.SerializedName;

public class CategoriesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("icon")
	private String icon;

	@SerializedName("icon_pressed")
	private String iconPressed;

	@SerializedName("id")
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setIconPressed(String iconPressed){
		this.iconPressed = iconPressed;
	}

	public String getIconPressed(){
		return iconPressed;
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
			"CategoriesItem{" + 
			"name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",icon_pressed = '" + iconPressed + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}