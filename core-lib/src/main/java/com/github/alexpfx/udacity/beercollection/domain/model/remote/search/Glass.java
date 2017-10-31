package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Glass {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("createDate")
    private String createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "Glass{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",createDate = '" + createDate + '\'' +
                        "}";
    }
}