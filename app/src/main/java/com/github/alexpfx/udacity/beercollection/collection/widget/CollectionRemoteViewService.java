package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by alexandre on 11/01/18.
 */

public class CollectionRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new CollectionRemoteViewFactory(getApplicationContext());
    }


}
