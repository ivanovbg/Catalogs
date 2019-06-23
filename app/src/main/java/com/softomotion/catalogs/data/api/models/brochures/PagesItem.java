package com.softomotion.catalogs.data.api.models.brochures;


import com.google.gson.annotations.SerializedName;

public class PagesItem{

	@SerializedName("image")
	private Image image;

	@SerializedName("page_number")
	private int pageNumber;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}

	public int getPageNumber(){
		return pageNumber;
	}

	@Override
 	public String toString(){
		return 
			"PagesItem{" + 
			"image = '" + image + '\'' + 
			",page_number = '" + pageNumber + '\'' + 
			"}";
		}
}