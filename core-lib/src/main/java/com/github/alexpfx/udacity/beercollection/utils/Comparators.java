package com.github.alexpfx.udacity.beercollection.utils;

import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.Comparator;

/**
 * Created by alexandre on 19/01/18.
 */

public class Comparators {
    public static Comparator<CollectionItem> COLLECTION_ITEM_BY_DATE_DESC = (Comparator<CollectionItem>) (o1, o2) -> {
        if (o1.getLastDate() == null && o1.getLastDate() == null) {
            return 0;
        }
        if (o1.getLastDate() == null) {
            return 1;
        }
        if (o1.getLastDate() == null) {
            return -1;
        }

        return o2.getLastDate().compareTo(o1.getLastDate());
    };

    public static Comparator<CollectionItem> COLLECTION_ITEM_BY_QUANTITY_DESC =
            (Comparator<CollectionItem>) (o2, o1) -> Integer.valueOf(o1.countBeers()).compareTo(o2.countBeers());
}
