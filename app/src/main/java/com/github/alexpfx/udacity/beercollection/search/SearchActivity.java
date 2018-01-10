package com.github.alexpfx.udacity.beercollection.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.utils.ToolbarUtils;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.detail.DetailFragment;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adicionar indicador de loading
 */
public class SearchActivity extends BaseActivity implements SearchFragment.Listener, DetailFragment.Listener {
    private static final String TAG = "SearchActivity";


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    @BindBool(R.bool.isMultiPane)
    boolean isMultiPane;

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            ToolbarUtils.setupToolbar(this, toolbar, false, true, false, false);
            textToolbarTitle.setText(getString(R.string.title_activity_search));

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drinkBeerPresenter.onDestroy();
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent().inject(this);

    }


    @Override
    public void onDetail(String id) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_BEER_ID, id);
        setIntent(intent);


        if (isMultiPane) {
            DetailFragment detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_pane2, detailFragment).commit();
        } else {
            DetailActivity.startDetail(this, id);
        }


    }

    @Override
    public void onDownload(String id) {
        drinkBeerPresenter.drink(new DrinkBeerUpdateItem(id, 0));
        finish();
    }

    @Override
    public void onTitleChanged(String title) {
        //When DetailFragment is inside SearchA ctivity it doesn't changes the activity title.

    }

    @Override
    public void onImageChanged(String imgUrl) {
        //When DetailFragment is inside SearchActivity it doesn't changes the toolbar image.
    }
}
