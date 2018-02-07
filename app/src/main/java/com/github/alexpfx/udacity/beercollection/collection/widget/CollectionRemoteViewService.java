package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class CollectionRemoteViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new CollectionRemoteViewFactory(getApplicationContext());
    }
}
