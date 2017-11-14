package com.github.alexpfx.udacity.beercollection.domain.model;

/**
 * Created by alexandre on 12/11/17.
 */

public class DrinkBeerUpdateItem {

    private String beerId;
    private Integer quantity;

    public DrinkBeerUpdateItem(String beerId, Integer quantity) {
        this.beerId = beerId;
        this.quantity = quantity;
    }

    public String getBeerId() {
        return beerId;
    }

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
