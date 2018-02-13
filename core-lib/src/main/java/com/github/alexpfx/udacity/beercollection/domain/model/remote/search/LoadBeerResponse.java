package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class LoadBeerResponse extends ServerResponse{

    @SerializedName("data")
    private DataItem dataItem;

    @SerializedName("message")
    private String message;




    @Override
    public boolean validate() {
        return dataItem != null;
    }
}