package com.github.alexpfx.udacity.beercollection.domain.client;

import java.util.List;

/**
 * Created by alexandre on 01/03/18.
 */

public interface UntappdService {
    List searchBeers(String clientSecret, String clientId, String query);
}
