package com.softomotion.catalogs.data.api.models.pins;


import com.google.gson.annotations.SerializedName;

public class Brand{

	@SerializedName("id_category")
	private String idCategory;

	@SerializedName("brand_logo_seo_link")
	private String brandLogoSeoLink;

	@SerializedName("name")
	private String name;

	@SerializedName("logo")
	private String logo;

	@SerializedName("id")
	private int id;

	public void setIdCategory(String idCategory){
		this.idCategory = idCategory;
	}

	public String getIdCategory(){
		return idCategory;
	}

	public void setBrandLogoSeoLink(String brandLogoSeoLink){
		this.brandLogoSeoLink = brandLogoSeoLink;
	}

	public String getBrandLogoSeoLink(){
		return brandLogoSeoLink;
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
			"id_category = '" + idCategory + '\'' + 
			",brand_logo_seo_link = '" + brandLogoSeoLink + '\'' + 
			",name = '" + name + '\'' + 
			",logo = '" + logo + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}