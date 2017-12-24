package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.BeerCollectionDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by alexandre on 09/11/17.
 */
@PerActivity
public class DefaultMyCollectionInteractor implements MyCollectionInteractor {
    private BeerCollectionDataSource dataSource;

    @Inject
    public DefaultMyCollectionInteractor(BeerCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Single<List<CollectionItemVO>> load() {
        return dataSource.all();
    }
}
