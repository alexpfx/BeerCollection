package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Style{

	@SerializedName("ibuMax")
	private String ibuMax;

	@SerializedName("srmMax")
	private String srmMax;

	@SerializedName("updateDate")
	private String updateDate;

	@SerializedName("description")
	private String description;

	@SerializedName("abvMax")
	private String abvMax;

	@SerializedName("fgMax")
	private String fgMax;

	@SerializedName("srmMin")
	private String srmMin;

	@SerializedName("ogMin")
	private String ogMin;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("shortName")
	private String shortName;

	@SerializedName("abvMin")
	private String abvMin;

	@SerializedName("fgMin")
	private String fgMin;

	@SerializedName("categoryId")
	private int categoryId;

	@SerializedName("ibuMin")
	private String ibuMin;

	@SerializedName("createDate")
	private String createDate;

	public void setIbuMax(String ibuMax){
		this.ibuMax = ibuMax;
	}

	public String getIbuMax(){
		return ibuMax;
	}

	public void setSrmMax(String srmMax){
		this.srmMax = srmMax;
	}

	public String getSrmMax(){
		return srmMax;
	}

	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setAbvMax(String abvMax){
		this.abvMax = abvMax;
	}

	public String getAbvMax(){
		return abvMax;
	}

	public void setFgMax(String fgMax){
		this.fgMax = fgMax;
	}

	public String getFgMax(){
		return fgMax;
	}

	public void setSrmMin(String srmMin){
		this.srmMin = srmMin;
	}

	public String getSrmMin(){
		return srmMin;
	}

	public void setOgMin(String ogMin){
		this.ogMin = ogMin;
	}

	public String getOgMin(){
		return ogMin;
	}

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

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}

	public void setAbvMin(String abvMin){
		this.abvMin = abvMin;
	}

	public String getAbvMin(){
		return abvMin;
	}

	public void setFgMin(String fgMin){
		this.fgMin = fgMin;
	}

	public String getFgMin(){
		return fgMin;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setIbuMin(String ibuMin){
		this.ibuMin = ibuMin;
	}

	public String getIbuMin(){
		return ibuMin;
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
			"Style{" + 
			"ibuMax = '" + ibuMax + '\'' + 
			",srmMax = '" + srmMax + '\'' + 
			",updateDate = '" + updateDate + '\'' + 
			",description = '" + description + '\'' + 
			",abvMax = '" + abvMax + '\'' + 
			",fgMax = '" + fgMax + '\'' + 
			",srmMin = '" + srmMin + '\'' + 
			",ogMin = '" + ogMin + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",shortName = '" + shortName + '\'' + 
			",abvMin = '" + abvMin + '\'' + 
			",fgMin = '" + fgMin + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			",ibuMin = '" + ibuMin + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}