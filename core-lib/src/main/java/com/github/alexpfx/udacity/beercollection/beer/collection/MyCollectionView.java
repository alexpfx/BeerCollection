package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.List;

/**
 * Created by alexandre on 09/11/17.
 */

public interface MyCollectionView extends BaseView {
    void showUserCollection (List<CollectionItem> items);
    void showErrorLoadingCollection (String message);
    void showCollectionEmpty ();
    void showLoading ();
    void hideLoading();
    void clearResults ();
}
