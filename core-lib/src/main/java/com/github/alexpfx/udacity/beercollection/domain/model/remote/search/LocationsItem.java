package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
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


}