package com.softomotion.catalogs.data.api.models.brochures;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("brochures")
	private List<BrochuresItem> brochures;

	@SerializedName("brands")
	private List<BrandsItem> brands;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
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
			"Response{" + 
			"response = '" + response + '\'' + 
			",brochures = '" + brochures + '\'' + 
			",brands = '" + brands + '\'' + 
			",categories = '" + categories + '\'' + 
			"}";
		}
}