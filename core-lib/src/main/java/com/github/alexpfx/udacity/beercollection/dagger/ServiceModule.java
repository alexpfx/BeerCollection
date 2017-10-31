package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.search.SearchDataSource;
import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.config.BreweryDbConfig;
import com.github.alexpfx.udacity.beercollection.util.Mappers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandre on 14/10/17.
 */
@Module
public class ServiceModule {

    private BreweryDbConfig breweryDbConfig;

    public ServiceModule(BreweryDbConfig breweryDbConfig) {
        this.breweryDbConfig = breweryDbConfig;
    }


    @Provides
    @Singleton
    public BreweryDbConfig breweryDbConfig (){
        return breweryDbConfig;
    }


    public static final String BASE_URL = "http://api.brewerydb.com/v2/";

    @Provides
    @Singleton
    public Gson gson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public OkHttpClient client() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /*
            specialize client here.
         */
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory
                        .create(gson)).build();

    }


    @Provides
    @Singleton
    public BreweryDBService breweryDBService(Retrofit retrofit) {
        return retrofit.create(BreweryDBService.class);
    }


    @Singleton
    @Provides
    @Named("remoteSearchDatasource")
    public SearchDataSource searchDataSource(BreweryDbConfig config, BreweryDBService service) {
        return name ->
                service.searchBeers(config.getKey(), name)
                        .map(Mappers.SEARCH_MAPPER);
    }




}
