package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 29/10/17.
 */

public interface BeerDataSource {
    void insert (LocalType<List<Beer>> beers);
    Single<LocalType<Beer>> load (String id);
}
