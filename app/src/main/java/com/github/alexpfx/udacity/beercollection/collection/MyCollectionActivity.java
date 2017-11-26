package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.ToolbarHelper;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.search.SearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectionActivity extends BaseActivity implements DrinkBeerView {

    public static final int REQUEST_CODE = 1001;
    private static final String TAG = "MyCollectionActivity";

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        setupToolbar ();

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

    private void setupToolbar() {
        ToolbarHelper.setupToolbar(this, toolbar, false, false);
        textToolbarTitle.setText(getString(R.string.activity_title_my_collection));
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
        app.getMyCollectionSubComponent(this).inject(this);
    }


}
