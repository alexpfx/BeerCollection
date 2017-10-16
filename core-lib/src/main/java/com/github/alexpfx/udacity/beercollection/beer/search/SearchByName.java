package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.github.alexpfx.udacity.beercollection.domain.model.SearchBeerResponse;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 16/10/17.
 */

public class SearchByName implements SearchInteractor {


    private final BreweryDBService service;
    private final SchedulerProvider schedulerProvider;


    public SearchByName(BreweryDBService service, SchedulerProvider schedulerProvider) {
        this.service = service;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Flowable<SearchBeerResponse> searchBeers(String key, String name, String order, String sort) {
        service.searchBeers(key, name, order, sort);

        return null;
    }
}
