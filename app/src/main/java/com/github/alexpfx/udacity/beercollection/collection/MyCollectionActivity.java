package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.local.CollectionItem;
import com.github.alexpfx.udacity.beercollection.search.SearchActivity;

import java.util.List;

import javax.inject.Inject;

public class MyCollectionActivity extends BaseActivity implements MyCollectionView, DrinkBeerView {

    public static final int REQUEST_CODE = 1001;
    private static final String TAG = "MyCollectionActivity";

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_my_collection, new
                    MyCollectionFragment()).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String beerId = data.getStringExtra(Constants.KEY_BEER_ID);
            String beerName = data.getStringExtra(Constants.KEY_BEER_NAME);

            startDialogFragment(beerId, beerName);
        }
    }

    private void startDialogFragment(String id, String name) {
        DrinkBeerFragmentDialog instance = DrinkBeerFragmentDialog.getInstance(id, name, new DrinkBeerFragmentDialog
                .PositiveClickListener() {
            @Override
            public void onPositiveClick(Integer quant) {
                drinkBeerPresenter.drink(new DrinkBeerUpdateItem(id, quant));
            }
        });
        instance.show(getSupportFragmentManager(), "DrinkBeerDialogFragment");
    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent(this, this).inject(this);
    }

    @Override
    public void showUserCollection(List<CollectionItem> items) {

    }

    @Override
    public void showErrorLoadingCollection() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLodading() {

    }

    @Override
    public void clearResults() {

    }

}
