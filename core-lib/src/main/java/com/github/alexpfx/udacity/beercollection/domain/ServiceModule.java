package com.github.alexpfx.udacity.beercollection.domain;

import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandre on 14/10/17.
 */
@Module
public class ServiceModule {


    public static final String BASE_URL = "http://api.brewerydb.com/v2/";


    @Provides
    @Singleton
    public Gson gson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(Gson gson) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory
                        .create(gson)).build();

    }

    @Provides
    @Singleton
    public BreweryDBService breweryDBService(Retrofit retrofit) {
        return retrofit.create(BreweryDBService.class);
    }

}
