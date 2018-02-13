package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class Labels{

	@SerializedName("large")
	private String large;

	@SerializedName("icon")
	private String icon;

	@SerializedName("medium")
	private String medium;


}