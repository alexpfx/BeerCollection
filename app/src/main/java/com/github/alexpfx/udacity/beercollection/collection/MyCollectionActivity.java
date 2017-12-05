package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.ToolbarHelper;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.search.SearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class MyCollectionActivity extends BaseActivity implements DrinkBeerView, MyCollectionFragment.Listener {

    public static final int REQUEST_CODE = 1001;
    private static final String TAG = "MyCollectionActivity";

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        setupToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_my_collection, new
                    MyCollectionFragment()).commit();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        compositeDisposable.dispose();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    private void setupToolbar() {
        ToolbarHelper.setupToolbar(this, toolbar, false, false);
        textToolbarTitle.setText(getString(R.string.activity_title_my_collection));
    }

    private void startDialogFragment(String id) {
        DrinkBeerFragmentDialog instance = DrinkBeerFragmentDialog.getInstance(id, new DrinkBeerFragmentDialog
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


    @Override
    public void onDetail(String beerId) {
        Log.d(TAG, "onDetail: ");
        DetailActivity.startDetail(getApplicationContext(), beerId);
    }

    @Override
    public void onHistory(String beerId) {
        Log.d(TAG, "onHistory: ");
    }

    @Override
    public void onAdd(String id) {
        Log.d(TAG, "onAdd: ");
        startDialogFragment(id);
    }
}
