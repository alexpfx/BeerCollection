package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Srm {

    @SerializedName("name")
    private String name;

    @SerializedName("hex")
    private String hex;

    @SerializedName("id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "Srm{" +
                        "name = '" + name + '\'' +
                        ",hex = '" + hex + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}