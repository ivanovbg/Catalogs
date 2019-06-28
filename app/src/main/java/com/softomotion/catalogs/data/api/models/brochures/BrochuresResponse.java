package com.softomotion.catalogs.data.api.models.brochures;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BrochuresResponse {

	@SerializedName("response")
	private BrochuresResponse brochuresResponse;

	@SerializedName("brochures")
	private List<BrochuresItem> brochures;

	@SerializedName("brands")
	private List<BrandsItem> brands;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setBrochuresResponse(BrochuresResponse brochuresResponse){
		this.brochuresResponse = brochuresResponse;
	}

	public BrochuresResponse getBrochuresResponse(){
		return brochuresResponse;
	}

	public void setBrochures(List<BrochuresItem> brochures){
		this.brochures = brochures;
	}

	public List<BrochuresItem> getBrochures(){
		return brochures;
	}

	public void setBrands(List<BrandsItem> brands){
		this.brands = brands;
	}

	public List<BrandsItem> getBrands(){
		return brands;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"BrochureResponse{" +
			"brochuresResponse = '" + brochuresResponse + '\'' +
			",brochures = '" + brochures + '\'' + 
			",brands = '" + brands + '\'' + 
			",categories = '" + categories + '\'' + 
			"}";
		}
}