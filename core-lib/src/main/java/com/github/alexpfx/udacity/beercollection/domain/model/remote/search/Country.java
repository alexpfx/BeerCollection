package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Country{

	@SerializedName("isoCode")
	private String isoCode;

	@SerializedName("displayName")
	private String displayName;

	@SerializedName("name")
	private String name;

	@SerializedName("isoThree")
	private String isoThree;

	@SerializedName("numberCode")
	private int numberCode;

	@SerializedName("createDate")
	private String createDate;

	public void setIsoCode(String isoCode){
		this.isoCode = isoCode;
	}

	public String getIsoCode(){
		return isoCode;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIsoThree(String isoThree){
		this.isoThree = isoThree;
	}

	public String getIsoThree(){
		return isoThree;
	}

	public void setNumberCode(int numberCode){
		this.numberCode = numberCode;
	}

	public int getNumberCode(){
		return numberCode;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateDate(){
		return createDate;
	}

	@Override
 	public String toString(){
		return 
			"Country{" + 
			"isoCode = '" + isoCode + '\'' + 
			",displayName = '" + displayName + '\'' + 
			",name = '" + name + '\'' + 
			",isoThree = '" + isoThree + '\'' + 
			",numberCode = '" + numberCode + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}