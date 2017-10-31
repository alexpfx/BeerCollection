package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DataItem {

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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public int getSrmId() {
        return srmId;
    }

    public void setSrmId(int srmId) {
        this.srmId = srmId;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGlasswareId() {
        return glasswareId;
    }

    public void setGlasswareId(int glasswareId) {
        this.glasswareId = glasswareId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableId() {
        return availableId;
    }

    public void setAvailableId(int availableId) {
        this.availableId = availableId;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public Srm getSrm() {
        return srm;
    }

    public void setSrm(Srm srm) {
        this.srm = srm;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public void setServingTemperature(String servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServingTemperatureDisplay() {
        return servingTemperatureDisplay;
    }

    public void setServingTemperatureDisplay(String servingTemperatureDisplay) {
        this.servingTemperatureDisplay = servingTemperatureDisplay;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "updateDate = '" + updateDate + '\'' +
                        ",glass = '" + glass + '\'' +
                        ",srmId = '" + srmId + '\'' +
                        ",statusDisplay = '" + statusDisplay + '\'' +
                        ",nameDisplay = '" + nameDisplay + '\'' +
                        ",isOrganic = '" + isOrganic + '\'' +
                        ",available = '" + available + '\'' +
                        ",description = '" + description + '\'' +
                        ",glasswareId = '" + glasswareId + '\'' +
                        ",type = '" + type + '\'' +
                        ",availableId = '" + availableId + '\'' +
                        ",labels = '" + labels + '\'' +
                        ",srm = '" + srm + '\'' +
                        ",servingTemperature = '" + servingTemperature + '\'' +
                        ",abv = '" + abv + '\'' +
                        ",styleId = '" + styleId + '\'' +
                        ",name = '" + name + '\'' +
                        ",servingTemperatureDisplay = '" + servingTemperatureDisplay + '\'' +
                        ",style = '" + style + '\'' +
                        ",id = '" + id + '\'' +
                        ",ibu = '" + ibu + '\'' +
                        ",status = '" + status + '\'' +
                        ",createDate = '" + createDate + '\'' +
                        "}";
    }
}