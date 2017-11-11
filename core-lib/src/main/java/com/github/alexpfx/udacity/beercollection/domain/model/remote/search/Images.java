package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Images{

	@SerializedName("squareLarge")
	private String squareLarge;

	@SerializedName("large")
	private String large;

	@SerializedName("icon")
	private String icon;

	@SerializedName("medium")
	private String medium;

	@SerializedName("squareMedium")
	private String squareMedium;

	public void setSquareLarge(String squareLarge){
		this.squareLarge = squareLarge;
	}

	public String getSquareLarge(){
		return squareLarge;
	}

	public void setLarge(String large){
		this.large = large;
	}

	public String getLarge(){
		return large;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}

	public void setSquareMedium(String squareMedium){
		this.squareMedium = squareMedium;
	}

	public String getSquareMedium(){
		return squareMedium;
	}

	@Override
 	public String toString(){
		return 
			"Images{" + 
			"squareLarge = '" + squareLarge + '\'' + 
			",large = '" + large + '\'' + 
			",icon = '" + icon + '\'' + 
			",medium = '" + medium + '\'' + 
			",squareMedium = '" + squareMedium + '\'' + 
			"}";
		}
}