package com.github.alexpfx.udacity.beercollection.databaselib.util;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.BreweriesItem;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.Category;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.DataItem;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.LoadBeerResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.SearchResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.ServerResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.Srm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class Mappers {


    /**
     * Because there are many beers in the rest service database with incomplete data, I filtered
     * beers that doesn't have data for one or more of this fields
     */
    public static final Predicate<Beer> BEER_FILTER = beer -> !(
            beer.getLabelMedium() == null ||
                    beer.getName() == null ||
                    beer.getDescription() == null);


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
        beer.setDescription(item.getDescription());
        beer.setGlass(item.getGlass() != null ? item.getGlass().getName() : "");
        beer.setOrganic(item.getIsOrganic() != null ? item.getIsOrganic() : "");
        beer.setName(item.getNameDisplay());

        setSrm(beer, item);

        setLabels(item, beer);


        setStyle(item, beer);
        setBrewery(item, beer);
        return beer;
    }

    private static void setLabels(DataItem item, Beer beer) {
        if (item.getLabels() != null) {
            beer.setLabelIcon(item.getLabels().getIcon());
            beer.setLabelMedium(item.getLabels().getMedium());
            beer.setLabelLarge(item.getLabels().getLarge());
        }
    }

    private static void setStyle(DataItem item, Beer beer) {
        if (item.getStyle() != null) {
            beer.setStyle(item.getStyle().getName());
            Category category = item.getStyle().getCategory();
            if (category != null) {
                beer.setStyleCategory(category.getName());
            }
            beer.setShortStyle(item.getStyle().getShortName());

        }
    }

    private static void setBrewery(DataItem item, Beer beer) {
        if (item.getBreweries() != null && !item.getBreweries().isEmpty()) {
            BreweriesItem first = item.getBreweries().get(0);
            beer.setBreweryName(first.getName());
            beer.setBreweryWebsite(first.getWebsite());
        }
    }

    private static void setSrm(Beer beer, DataItem item) {
        if (item.getSrm() != null) {
            Srm srm = item.getSrm();
            beer.setSrm(srm.getName());
            beer.setSrmHexColor(srm.getHex());
        }
    }


    private static boolean isValid(ServerResponse response) {
        return response != null && response.isValid();
    }

}
