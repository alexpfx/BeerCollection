package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
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


}