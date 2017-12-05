package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.BeerCollectionDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import javax.inject.Inject;

/**
 * Created by alexandre on 12/11/17.
 */
@PerActivity
public class DrinkBeerInteractorImpl implements DrinkBeerInteractor {

    private BeerCollectionDataSource dataSource;

    @Inject
    public DrinkBeerInteractorImpl(BeerCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(DrinkBeerUpdateItem beer) {
        dataSource.insert(beer);
    }
}
