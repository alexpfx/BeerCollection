package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class ServerResponse {

    @SerializedName("status")
    private String status;

    public boolean isValid (){
        return status != null && status.equals("success") && validate();
    }

    public abstract boolean validate();
}

