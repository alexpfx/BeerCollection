package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 29/10/17.
 */

public interface BeerLocalDataSource {
    void insert (LocalType<List<Beer>> beers);
    Flowable<LocalType<Beer>> load (String id);
}
