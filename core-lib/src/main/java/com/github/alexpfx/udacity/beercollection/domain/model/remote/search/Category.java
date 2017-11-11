package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Category{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("createDate")
	private String createDate;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"Category{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}