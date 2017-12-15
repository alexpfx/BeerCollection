package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 09/11/17.
 */

public interface BeerCollectionDataSource {
    Single insert (DrinkBeerUpdateItem item);
    Single<List<CollectionItemVO>> all ();
}
