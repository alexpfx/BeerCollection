package com.github.alexpfx.udacity.beercollection.domain.client;


import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.LoadBeerResponse;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.search.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface BreweryDBService {

    String KEY = "key";
    String Q = "q";


    @GET("search?type=beer&withBreweries=Y")
    Single<SearchResponse> searchBeers(
            @Query(KEY) String key,
            @Query(Q) String queryString
    );


//    @GET("beer/{id}?withBreweries=Y")
    @GET("beer/{id}/")
    Single<LoadBeerResponse> load(@Path("id") String id, @Query(KEY) String key);

}
