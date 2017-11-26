package com.github.alexpfx.udacity.beercollection.detail;

import android.os.Bundle;
import android.util.Log;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailView {
    private static final String TAG = "DetailActivity";

    @Inject
    DetailPresenter detailPresenter;

    private DetailViewHolder detailViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailViewHolder = new DetailViewHolder(getWindow().getDecorView());
        detailViewHolder.getBeerClickObservable().subscribe(view -> {
            Beer beer = (Beer) view.getTag();

            DrinkBeerFragmentDialog.getInstance(beer.getId(), beer.getName()).show(getSupportFragmentManager(), "DrinkBeerFragmentDialog");
        });

        String beerid = (String) getIntent().getExtras().get(Constants.KEY_BEER_ID);
        detailPresenter.load(beerid);

    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getDetailSubComponent(this).inject(this);
    }

    @Override
    public void showBeer(Beer beer) {
        detailViewHolder.setBeer(beer);
    }


    @Override
    public void showLoadError(Throwable throwable) {
        Log.e(TAG, "showLoadError: ", throwable);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        detailPresenter.onDestroy();
        detailViewHolder.unbind();
    }
}


