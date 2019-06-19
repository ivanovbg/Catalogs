package com.softomotion.catalogs.data.api.models.pins;


import com.google.gson.annotations.SerializedName;


public class PinImages{

	@SerializedName("default")
	private String jsonMemberDefault;

	@SerializedName("liked_selected")
	private String likedSelected;

	@SerializedName("selected")
	private String selected;

	@SerializedName("liked")
	private String liked;

	public void setJsonMemberDefault(String jsonMemberDefault){
		this.jsonMemberDefault = jsonMemberDefault;
	}

	public String getJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public void setLikedSelected(String likedSelected){
		this.likedSelected = likedSelected;
	}

	public String getLikedSelected(){
		return likedSelected;
	}

	public void setSelected(String selected){
		this.selected = selected;
	}

	public String getSelected(){
		return selected;
	}

	public void setLiked(String liked){
		this.liked = liked;
	}

	public String getLiked(){
		return liked;
	}

	@Override
 	public String toString(){
		return 
			"PinImages{" + 
			"default = '" + jsonMemberDefault + '\'' + 
			",liked_selected = '" + likedSelected + '\'' + 
			",selected = '" + selected + '\'' + 
			",liked = '" + liked + '\'' + 
			"}";
		}
}