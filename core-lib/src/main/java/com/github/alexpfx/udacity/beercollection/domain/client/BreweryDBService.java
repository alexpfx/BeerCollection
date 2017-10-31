package com.github.alexpfx.udacity.beercollection.domain.client;


import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by alexandre on 14/10/17.
 */

public interface BreweryDBService {

    String KEY = "key";
    String Q = "q";

    @GET("search?type=beer")
    Single<SearchResponse> searchBeers(
            @Query(KEY) String key,
            @Query(Q) String queryString
    );


}
