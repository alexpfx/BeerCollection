package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class Style {

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
}