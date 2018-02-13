package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class Srm{

	@SerializedName("name")
	private String name;

	@SerializedName("hex")
	private String hex;

	@SerializedName("id")
	private int id;

}