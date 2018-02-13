package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class Category{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("createDate")
	private String createDate;


}