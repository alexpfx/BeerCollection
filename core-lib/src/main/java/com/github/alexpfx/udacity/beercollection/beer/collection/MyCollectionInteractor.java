package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;

import java.util.List;

import io.reactivex.Single;

public interface MyCollectionInteractor extends BaseInteractor{
    Single<List<CollectionItemVO>> load ();
    Single clearCollectionData ();
    Single<Void> deleteBeer (String id);
}
