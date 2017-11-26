package com.github.alexpfx.udacity.beercollection.util;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.Category;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.DataItem;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.LoadBeerResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.SearchResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.ServerResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by alexandre on 24/10/17.
 */


public class Mappers {

    public static final Predicate<Beer> BEER_FILTER = beer -> !(
            beer.getLabelMedium() == null ||
            beer.getName() == null ||
                    beer.getAbv() == null ||
                    beer.getDescription() == null ||
                    beer.getShortStyle() == null);


    public static final Function<LoadBeerResponse, Beer> LOAD_MAPPER = loadBeerResponse -> {
        Beer beer = new Beer();

        if (!isValid(loadBeerResponse)) {
            return beer;
        }

        beer = from(loadBeerResponse.getDataItem());
        return beer;

    };
    public static Function<SearchResponse, List<Beer>> SEARCH_MAPPER = response -> {
        List<Beer> beerLocalType = Collections.emptyList();

        if (!isValid(response)) {
            return beerLocalType;
        }

        List<Beer> beers = from(response.getData());

        return beers;
    };

    private static List<Beer> from(List<DataItem> data) {
        List<Beer> beers = new ArrayList<>();

        for (DataItem item : data) {
            Beer beer = from(item);
            beers.add(beer);
        }
        return beers;
    }


    private static Beer from(DataItem item) {
        Beer beer = new Beer();

        beer.setId(item.getId());
        beer.setAbv(item.getAbv());
        beer.setIbu(item.getIbu());
        beer.setServingTemperature(item.getServingTemperatureDisplay());
        beer.setSrm(item.getSrm() != null ? item.getSrm().getName() : "");
        beer.setDescription(item.getDescription());
        beer.setGlass(item.getGlass() != null ? item.getGlass().getName() : "");



        if (item.getLabels() != null) {
            beer.setLabelIcon(item.getLabels().getIcon());
            beer.setLabelMedium(item.getLabels().getMedium());
            beer.setLabelLarge(item.getLabels().getLarge());
        }
        beer.setName(item.getNameDisplay());
        if (item.getStyle() != null) {
            beer.setStyle(item.getStyle().getName());
            Category category = item.getStyle().getCategory();
            if (category != null) {
                beer.setStyleCategory(category.getName());
            }
            beer.setShortStyle(item.getStyle().getShortName());

        }


        if (item.getBreweries() != null && !item.getBreweries().isEmpty()){
            beer.setBrewery(item.getBreweries().get(0).getName());
        }
        return beer;
    }


    private static boolean isValid(ServerResponse response) {
        return response != null && response.isValid();
    }

}
