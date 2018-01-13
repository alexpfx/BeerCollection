package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.text.DateFormat;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


public class CollectionRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory, MyCollectionView {

    private static final String TAG = "CollectionRemoteViewFac";
    @Inject
    LoadCollectionPresenter loadCollectionPresenter;
    private Context context;
    private List<CollectionItem> items;
    private CountDownLatch waitForData;


    public CollectionRemoteViewFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
        BeerApp beerApp = (BeerApp) context.getApplicationContext();
        beerApp.getComponent().plus(new MyCollectionModule()).inject(this);
        loadCollectionPresenter.bind(this);
        waitForData = new CountDownLatch(1);
    }

    @Override
    public void onDataSetChanged() {
        loadCollectionPresenter.load();
        waitData();
    }

    private void waitData() {
        try {
            waitForData.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroy() {
        context = null;
        items = null;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        CollectionItem item = items.get(position);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget_item_beer);

        remoteViews.setTextViewText(R.id.widget_text_beer_name, TextUtils.concat(String.valueOf(position+1),"º " ,item.getBeer().getName()));

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        remoteViews.setTextViewText(R.id.widget_text_last_date, dateFormat.format(item.getLastDate()));
        remoteViews.setTextViewText(R.id.widget_text_quantity, String.valueOf(item.countBeers()));

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void showUserCollection(List<CollectionItem> items) {
        this.items = items;
        Log.d(TAG, "showUserCollection: ");
        waitForData.countDown();
    }

    @Override
    public void showErrorLoadingCollection(String message) {

    }

    @Override
    public void showCollectionEmpty() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearResults() {

    }
}