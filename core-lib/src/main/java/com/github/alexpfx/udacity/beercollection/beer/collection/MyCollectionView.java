package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.List;

public interface MyCollectionView extends BaseView {
    void showUserCollection (List<CollectionItem> items);
    void showErrorLoadingCollection (String message);
    void showCollectionEmpty ();
    void showLoading ();
    void hideLoading();
}
