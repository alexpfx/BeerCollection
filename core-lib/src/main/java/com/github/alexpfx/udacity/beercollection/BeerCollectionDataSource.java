package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.model.local.CollectionItem;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 09/11/17.
 */

public interface BeerCollectionDataSource {
    void insert (CollectionItem collectionItem);
    Single<List<CollectionItem>> all ();
}
