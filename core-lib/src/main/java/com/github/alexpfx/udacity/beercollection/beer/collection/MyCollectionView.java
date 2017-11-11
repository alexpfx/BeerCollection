package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.local.CollectionItem;

import java.util.List;

/**
 * Created by alexandre on 09/11/17.
 */

public interface MyCollectionView extends BaseView {
    void showUserCollection (List<CollectionItem> items);
    void showErrorLoadingCollection ();
    void showLoading ();
    void hideLodading ();
    void clearResults ();
}
