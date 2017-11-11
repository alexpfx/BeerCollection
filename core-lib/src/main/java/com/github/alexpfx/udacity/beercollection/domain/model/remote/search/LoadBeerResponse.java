package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class LoadBeerResponse extends ServerResponse{

    @SerializedName("data")
    private DataItem dataItem;

    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DataItem dataItem) {
        this.dataItem = dataItem;
    }


    @Override
    public String toString() {
        return
                "LoadBeerResponse{" +
                        "dataItem = '" + dataItem + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + getStatus() + '\'' +
                        "}";
    }


    @Override
    public boolean validate() {
        return dataItem != null;
    }
}