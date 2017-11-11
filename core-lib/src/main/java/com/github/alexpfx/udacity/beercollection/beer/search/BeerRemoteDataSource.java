package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 16/10/17.
 */

public interface RemoteBeerDataSource {
    Single<LocalType<List<Beer>>> search(String name);
    Single<LocalType<Beer>> load (String id);

}
