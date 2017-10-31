package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Labels {

    @SerializedName("large")
    private String large;

    @SerializedName("icon")
    private String icon;

    @SerializedName("medium")
    private String medium;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return
                "Labels{" +
                        "large = '" + large + '\'' +
                        ",icon = '" + icon + '\'' +
                        ",medium = '" + medium + '\'' +
                        "}";
    }
}