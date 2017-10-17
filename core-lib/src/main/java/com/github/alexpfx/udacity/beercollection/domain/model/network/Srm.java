
package com.github.alexpfx.udacity.beercollection.domain.model.network;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Srm {

    @SerializedName("hex")
    private String mHex;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;

    public String getHex() {
        return mHex;
    }

    public void setHex(String hex) {
        mHex = hex;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
