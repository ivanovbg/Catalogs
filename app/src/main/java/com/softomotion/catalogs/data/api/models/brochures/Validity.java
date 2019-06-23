package com.softomotion.catalogs.data.api.models.brochures;

import com.google.gson.annotations.SerializedName;


public class Validity{

	@SerializedName("start")
	private int start;

	@SerializedName("end")
	private int end;

	public void setStart(int start){
		this.start = start;
	}

	public int getStart(){
		return start;
	}

	public void setEnd(int end){
		this.end = end;
	}

	public int getEnd(){
		return end;
	}

	@Override
 	public String toString(){
		return 
			"Validity{" + 
			"start = '" + start + '\'' + 
			",end = '" + end + '\'' + 
			"}";
		}
}