package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class DataItem{

	@SerializedName("updateDate")
	private String updateDate;

	@SerializedName("glass")
	private Glass glass;

	@SerializedName("srmId")
	private int srmId;

	@SerializedName("statusDisplay")
	private String statusDisplay;

	@SerializedName("nameDisplay")
	private String nameDisplay;

	@SerializedName("isOrganic")
	private String isOrganic;

	@SerializedName("available")
	private Available available;

	@SerializedName("description")
	private String description;

	@SerializedName("glasswareId")
	private int glasswareId;

	@SerializedName("breweries")
	private List<BreweriesItem> breweries;

	@SerializedName("type")
	private String type;

	@SerializedName("availableId")
	private int availableId;

	@SerializedName("labels")
	private Labels labels;

	@SerializedName("srm")
	private Srm srm;

	@SerializedName("servingTemperature")
	private String servingTemperature;

	@SerializedName("abv")
	private String abv;

	@SerializedName("styleId")
	private int styleId;

	@SerializedName("name")
	private String name;

	@SerializedName("servingTemperatureDisplay")
	private String servingTemperatureDisplay;

	@SerializedName("style")
	private Style style;

	@SerializedName("id")
	private String id;

	@SerializedName("ibu")
	private String ibu;

	@SerializedName("status")
	private String status;

	@SerializedName("createDate")
	private String createDate;


}