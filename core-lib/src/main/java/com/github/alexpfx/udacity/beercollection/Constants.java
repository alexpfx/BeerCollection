package com.github.alexpfx.udacity.beercollection;


public interface Constants {

    int DEFAULT_TIMEOUT = 30;
    long CACHE_EXPIRATION_HOURS = 24;
    int DEFAULT_MAX_SEARCH_RESULTS = 10;
    long QUERY_DEBONCE_TIME = 500;
    int DETAIL_BEER_LABEL_IMAGE_SIZE = 800;
    int COLLECTION_BEER_LABEL_IMAGE_SIZE = 480;
    int SEARCH_BEER_LABEL_IMAGE_SIZE = 320;


    interface Keys {
        String ADAPTER = "ADAPTER";
        String SEARCH_QUERY = "SEARCH_QUERY";
        String BEER_ID = "BEER_KEY";
        String LAST_LIST_POSITION = "LAST_LIST_POSITION";
        String IS_SELECTABLE = "IS_SELECTABLE";
        String SELECTEDS = "SELECTEDS";
    }


    interface Beer {

        String BEER_ID = "beerId";
        String QUANTITY = "quantity";
        String TIMESTAMP = "timestamp";
    }
}
