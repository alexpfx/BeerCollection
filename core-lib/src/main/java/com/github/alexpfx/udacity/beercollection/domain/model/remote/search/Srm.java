package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Srm{

	@SerializedName("name")
	private String name;

	@SerializedName("hex")
	private String hex;

	@SerializedName("id")
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHex(String hex){
		this.hex = hex;
	}

	public String getHex(){
		return hex;
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
			"Srm{" + 
			"name = '" + name + '\'' + 
			",hex = '" + hex + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}