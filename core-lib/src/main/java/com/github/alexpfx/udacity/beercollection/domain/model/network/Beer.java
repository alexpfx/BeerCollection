
package com.github.alexpfx.udacity.beercollection.domain.model.network;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Beer {

    @SerializedName("abv")
    private String mAbv;
    @SerializedName("available")
    private Available mAvailable;
    @SerializedName("availableId")
    private Long mAvailableId;
    @SerializedName("createDate")
    private String mCreateDate;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("glass")
    private Glass mGlass;
    @SerializedName("glasswareId")
    private Long mGlasswareId;
    @SerializedName("ibu")
    private String mIbu;
    @SerializedName("id")
    private String mId;
    @SerializedName("isOrganic")
    private String mIsOrganic;
    @SerializedName("labels")
    private Labels mLabels;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameDisplay")
    private String mNameDisplay;
    @SerializedName("servingTemperature")
    private String mServingTemperature;
    @SerializedName("servingTemperatureDisplay")
    private String mServingTemperatureDisplay;
    @SerializedName("srm")
    private Srm mSrm;
    @SerializedName("srmId")
    private Long mSrmId;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("statusDisplay")
    private String mStatusDisplay;
    @SerializedName("style")
    private Style mStyle;
    @SerializedName("styleId")
    private Long mStyleId;
    @SerializedName("updateDate")
    private String mUpdateDate;

    public String getAbv() {
        return mAbv;
    }

    public void setAbv(String abv) {
        mAbv = abv;
    }

    public Available getAvailable() {
        return mAvailable;
    }

    public void setAvailable(Available available) {
        mAvailable = available;
    }

    public Long getAvailableId() {
        return mAvailableId;
    }

    public void setAvailableId(Long availableId) {
        mAvailableId = availableId;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Glass getGlass() {
        return mGlass;
    }

    public void setGlass(Glass glass) {
        mGlass = glass;
    }

    public Long getGlasswareId() {
        return mGlasswareId;
    }

    public void setGlasswareId(Long glasswareId) {
        mGlasswareId = glasswareId;
    }

    public String getIbu() {
        return mIbu;
    }

    public void setIbu(String ibu) {
        mIbu = ibu;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIsOrganic() {
        return mIsOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        mIsOrganic = isOrganic;
    }

    public Labels getLabels() {
        return mLabels;
    }

    public void setLabels(Labels labels) {
        mLabels = labels;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNameDisplay() {
        return mNameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        mNameDisplay = nameDisplay;
    }

    public String getServingTemperature() {
        return mServingTemperature;
    }

    public void setServingTemperature(String servingTemperature) {
        mServingTemperature = servingTemperature;
    }

    public String getServingTemperatureDisplay() {
        return mServingTemperatureDisplay;
    }

    public void setServingTemperatureDisplay(String servingTemperatureDisplay) {
        mServingTemperatureDisplay = servingTemperatureDisplay;
    }

    public Srm getSrm() {
        return mSrm;
    }

    public void setSrm(Srm srm) {
        mSrm = srm;
    }

    public Long getSrmId() {
        return mSrmId;
    }

    public void setSrmId(Long srmId) {
        mSrmId = srmId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatusDisplay() {
        return mStatusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        mStatusDisplay = statusDisplay;
    }

    public Style getStyle() {
        return mStyle;
    }

    public void setStyle(Style style) {
        mStyle = style;
    }

    public Long getStyleId() {
        return mStyleId;
    }

    public void setStyleId(Long styleId) {
        mStyleId = styleId;
    }

    public String getUpdateDate() {
        return mUpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        mUpdateDate = updateDate;
    }

}
