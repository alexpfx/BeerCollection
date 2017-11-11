package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LocationsItem{

	@SerializedName("openToPublic")
	private String openToPublic;

	@SerializedName("country")
	private Country country;

	@SerializedName("website")
	private String website;

	@SerializedName("updateDate")
	private String updateDate;

	@SerializedName("countryIsoCode")
	private String countryIsoCode;

	@SerializedName("statusDisplay")
	private String statusDisplay;

	@SerializedName("postalCode")
	private String postalCode;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("locationType")
	private String locationType;

	@SerializedName("inPlanning")
	private String inPlanning;

	@SerializedName("isClosed")
	private String isClosed;

	@SerializedName("locationTypeDisplay")
	private String locationTypeDisplay;

	@SerializedName("streetAddress")
	private String streetAddress;

	@SerializedName("phone")
	private String phone;

	@SerializedName("isPrimary")
	private String isPrimary;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("region")
	private String region;

	@SerializedName("longitude")
	private double longitude;

	@SerializedName("status")
	private String status;

	@SerializedName("createDate")
	private String createDate;

	public void setOpenToPublic(String openToPublic){
		this.openToPublic = openToPublic;
	}

	public String getOpenToPublic(){
		return openToPublic;
	}

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}

	public void setCountryIsoCode(String countryIsoCode){
		this.countryIsoCode = countryIsoCode;
	}

	public String getCountryIsoCode(){
		return countryIsoCode;
	}

	public void setStatusDisplay(String statusDisplay){
		this.statusDisplay = statusDisplay;
	}

	public String getStatusDisplay(){
		return statusDisplay;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLocationType(String locationType){
		this.locationType = locationType;
	}

	public String getLocationType(){
		return locationType;
	}

	public void setInPlanning(String inPlanning){
		this.inPlanning = inPlanning;
	}

	public String getInPlanning(){
		return inPlanning;
	}

	public void setIsClosed(String isClosed){
		this.isClosed = isClosed;
	}

	public String getIsClosed(){
		return isClosed;
	}

	public void setLocationTypeDisplay(String locationTypeDisplay){
		this.locationTypeDisplay = locationTypeDisplay;
	}

	public String getLocationTypeDisplay(){
		return locationTypeDisplay;
	}

	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress(){
		return streetAddress;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setIsPrimary(String isPrimary){
		this.isPrimary = isPrimary;
	}

	public String getIsPrimary(){
		return isPrimary;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
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
			"LocationsItem{" + 
			"openToPublic = '" + openToPublic + '\'' + 
			",country = '" + country + '\'' + 
			",website = '" + website + '\'' + 
			",updateDate = '" + updateDate + '\'' + 
			",countryIsoCode = '" + countryIsoCode + '\'' + 
			",statusDisplay = '" + statusDisplay + '\'' + 
			",postalCode = '" + postalCode + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",locationType = '" + locationType + '\'' + 
			",inPlanning = '" + inPlanning + '\'' + 
			",isClosed = '" + isClosed + '\'' + 
			",locationTypeDisplay = '" + locationTypeDisplay + '\'' + 
			",streetAddress = '" + streetAddress + '\'' + 
			",phone = '" + phone + '\'' + 
			",isPrimary = '" + isPrimary + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",region = '" + region + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",status = '" + status + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}