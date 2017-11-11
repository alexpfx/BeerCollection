package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BreweriesItem{

	@SerializedName("established")
	private String established;

	@SerializedName("website")
	private String website;

	@SerializedName("images")
	private Images images;

	@SerializedName("updateDate")
	private String updateDate;

	@SerializedName("statusDisplay")
	private String statusDisplay;

	@SerializedName("isOrganic")
	private String isOrganic;

	@SerializedName("description")
	private String description;

	@SerializedName("isMassOwned")
	private String isMassOwned;

	@SerializedName("brandClassification")
	private String brandClassification;

	@SerializedName("name")
	private String name;

	@SerializedName("nameShortDisplay")
	private String nameShortDisplay;

	@SerializedName("locations")
	private List<LocationsItem> locations;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private String status;

	@SerializedName("createDate")
	private String createDate;

	public void setEstablished(String established){
		this.established = established;
	}

	public String getEstablished(){
		return established;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Images getImages(){
		return images;
	}

	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}

	public void setStatusDisplay(String statusDisplay){
		this.statusDisplay = statusDisplay;
	}

	public String getStatusDisplay(){
		return statusDisplay;
	}

	public void setIsOrganic(String isOrganic){
		this.isOrganic = isOrganic;
	}

	public String getIsOrganic(){
		return isOrganic;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setIsMassOwned(String isMassOwned){
		this.isMassOwned = isMassOwned;
	}

	public String getIsMassOwned(){
		return isMassOwned;
	}

	public void setBrandClassification(String brandClassification){
		this.brandClassification = brandClassification;
	}

	public String getBrandClassification(){
		return brandClassification;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setNameShortDisplay(String nameShortDisplay){
		this.nameShortDisplay = nameShortDisplay;
	}

	public String getNameShortDisplay(){
		return nameShortDisplay;
	}

	public void setLocations(List<LocationsItem> locations){
		this.locations = locations;
	}

	public List<LocationsItem> getLocations(){
		return locations;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
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
			"BreweriesItem{" + 
			"established = '" + established + '\'' + 
			",website = '" + website + '\'' + 
			",images = '" + images + '\'' + 
			",updateDate = '" + updateDate + '\'' + 
			",statusDisplay = '" + statusDisplay + '\'' + 
			",isOrganic = '" + isOrganic + '\'' + 
			",description = '" + description + '\'' + 
			",isMassOwned = '" + isMassOwned + '\'' + 
			",brandClassification = '" + brandClassification + '\'' + 
			",name = '" + name + '\'' + 
			",nameShortDisplay = '" + nameShortDisplay + '\'' + 
			",locations = '" + locations + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}