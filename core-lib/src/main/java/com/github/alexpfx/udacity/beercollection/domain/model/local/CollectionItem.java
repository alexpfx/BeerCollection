package com.github.alexpfx.udacity.beercollection.domain.model.local;

import java.util.Date;

/**
 * Created by alexandre on 09/11/17.
 */

public class CollectionItem {
    Beer beer;
    Date drinkDate;
    Integer quantity;

    public CollectionItem(Beer beer, Date drinkDate, Integer quantity) {
        this.beer = beer;
        this.drinkDate = drinkDate;
        this.quantity = quantity;
    }

    public CollectionItem(Beer beer, Date drinkDate) {
        this.beer = beer;
        this.drinkDate = drinkDate;
    }

    public Beer getBeer() {
        return beer;
    }

    public Date getDrinkDate() {
        return drinkDate;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
