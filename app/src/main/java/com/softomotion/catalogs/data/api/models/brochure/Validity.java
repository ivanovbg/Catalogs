package com.softomotion.catalogs.data.api.models.brochure;


import com.google.gson.annotations.SerializedName;


public class Validity{

	@SerializedName("start")
	private long start;

	@SerializedName("end")
	private long end;

	@SerializedName("expires_in")
	private int expiresIn;

	public void setStart(int start){
		this.start = start;
	}

	public long getStart(){
		return start;
	}

	public void setEnd(int end){
		this.end = end;
	}

	public long getEnd(){
		return end;
	}

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	@Override
 	public String toString(){
		return 
			"Validity{" + 
			"start = '" + start + '\'' + 
			",end = '" + end + '\'' + 
			",expires_in = '" + expiresIn + '\'' + 
			"}";
		}
}