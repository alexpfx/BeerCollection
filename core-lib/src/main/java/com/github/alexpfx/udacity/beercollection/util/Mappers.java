package com.github.alexpfx.udacity.beercollection.util;

import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.Category;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.DataItem;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by alexandre on 24/10/17.
 */

public class Mappers {

    public static Function<SearchResponse, LocalType<List<Beer>>> SEARCH_MAPPER = response -> {
        LocalType<List<Beer>> beerLocalType = new LocalType<>();

        if (!isValid(response)) {
            return beerLocalType;
        }

        List<Beer> beers = from(response.getData());
        beerLocalType.setData(beers);
        return beerLocalType;
    };

    private static List<Beer> from(List<DataItem> data) {
        List<Beer> beers = new ArrayList<>();

        data.forEach(item -> {
            Beer beer = from(item);
            beers.add(beer);

        });
        return beers;
    }

    private static Beer from(DataItem item) {
        Beer beer = new Beer();

        beer.setId(item.getId());
        beer.setAbv(item.getAbv());
        beer.setIbu(item.getIbu());
        beer.setServingTemperature(item.getServingTemperatureDisplay());
        beer.setSrm(item.getSrm() != null ? item.getSrm().getName() : null);
        beer.setDescription(item.getDescription());
        beer.setGlass(item.getGlass() != null ? item.getGlass().getName() : null);
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
        }
        return beer;
    }


    private static boolean isValid(SearchResponse response) {
        return response != null && response.getStatus().equals("success") && response.getTotalResults() > 0 &&
                response.getData() != null && response.getData().size() > 0;
    }

}
