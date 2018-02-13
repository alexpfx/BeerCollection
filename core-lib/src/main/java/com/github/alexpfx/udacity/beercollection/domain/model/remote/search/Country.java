package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

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


}