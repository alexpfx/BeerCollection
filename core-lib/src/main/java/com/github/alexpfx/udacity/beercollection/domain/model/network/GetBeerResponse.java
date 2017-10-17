
package com.github.alexpfx.udacity.beercollection.domain.model.network;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GetBeerResponse {

    @SerializedName("beer")
    private Beer mBeer;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public Beer getBeer() {
        return mBeer;
    }

    public void setBeer(Beer beer) {
        mBeer = beer;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
