package com.github.alexpfx.udacity.beercollection.domain.client;

import com.github.alexpfx.udacity.beercollection.domain.model.SearchBeerResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by alexandre on 14/10/17.
 */

public interface BreweryDBService {

    String KEY = "key";
    String NAME = "name";
    String ORDER = "order";
    String SORT = "sort";

    @GET("/beers")
    Flowable<SearchBeerResponse> searchBeers(
            @Query(KEY) String key,
            @Query(NAME) String name,
            @Query(ORDER) String order,
            @Query(SORT) String sort
    );
    @GET("/beers")
    Flowable<SearchBeerResponse> searchBeers(
            @Query(KEY) String key,
            @Query(NAME) String name,
            @Query(ORDER) String order
    );
    @GET("/beers")
    Flowable<SearchBeerResponse> searchBeers(
            @Query(KEY) String key,
            @Query(NAME) String name
    );


    enum Sort {
        ASC("asc"), DESC("desc");

        private String name;

        Sort(String name) {
            this.name = name;
        }
    }

    enum Order {
        NAME("name"), UPDATE_DATE("updateDate");

        private String name;

        Order(String name) {
            this.name = name;
        }
    }

}
