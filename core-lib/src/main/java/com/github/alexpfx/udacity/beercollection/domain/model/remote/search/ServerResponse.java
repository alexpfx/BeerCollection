package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandre on 04/11/17.
 */

public abstract class ServerResponse {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isValid (){
        return status != null && status.equals("success") && validate();
    }

    public abstract boolean validate();
}

