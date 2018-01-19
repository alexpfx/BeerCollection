package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.Comparator;

/**
 * Created by alexandre on 09/11/17.
 */

public interface LoadCollectionPresenter extends BasePresenter<MyCollectionView> {
    void load(Comparator<CollectionItem> comparator);
}
