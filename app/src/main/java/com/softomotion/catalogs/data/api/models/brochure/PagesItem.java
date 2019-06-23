package com.softomotion.catalogs.data.api.models.brochure;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PagesItem{

	@SerializedName("image")
	private Image image;

	@SerializedName("page_number")
	private int pageNumber;

	@SerializedName("pins")
	private List<Object> pins;


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

	public void setPins(List<Object> pins){
		this.pins = pins;
	}

	public List<Object> getPins(){
		return pins;
	}


	@Override
 	public String toString(){
		return 
			"PagesItem{" + 
			"image = '" + image + '\'' + 
			",page_number = '" + pageNumber + '\'' + 
			",pins = '" + pins + '\'' +
			"}";
		}
}