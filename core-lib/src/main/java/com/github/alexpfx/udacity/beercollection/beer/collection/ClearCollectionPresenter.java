package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

import io.reactivex.Single;

/**
 * Created by alexandre on 24/12/17.
 */

public interface ClearCollectionPresenter extends BasePresenter<ClearCollectionView>{

    void clearCollection ();
}
