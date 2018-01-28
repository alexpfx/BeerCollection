package com.github.alexpfx.udacity.beercollection.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenter;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenterView;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.detail.DetailFragment;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.utils.ToolbarUtils;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adicionar indicador de loading
 */
public class SearchActivity extends BaseActivity implements SearchFragment.Listener, DetailFragment.Listener,
        DrinkBeerView, LoadBeerInfoPresenterView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    @BindBool(R.bool.isMultiPane)
    boolean isMultiPane;

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

    @Inject
    LoadBeerInfoPresenter loadBeerInfoPresenter;


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
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent().inject(this);

        drinkBeerPresenter.init(this);
        loadBeerInfoPresenter.init(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        drinkBeerPresenter.unLoad();
        loadBeerInfoPresenter.unLoad();
    }


    @Override
    public void onDetail(String id) {

        if (isMultiPane) {
            DetailFragment detailFragment = DetailFragment.getInstance(id);
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_pane2, detailFragment).commit();
        } else {
            Intent intent = DetailActivity.getStartIntent(this, id);
            startActivity(intent);
        }

    }


    @Override
    public void onDownload(String id, String name) {
        drinkBeerPresenter.drink(new DrinkBeerUpdateItem(id, 0));
    }


    @Override
    public void showDrinkAdded(String beerId, int quantity) {
        loadBeerInfoPresenter.load(beerId);
    }

    @Override
    public void showErrorOnDrinkBeer(Object error) {

    }

    @Override
    public void showBeerInfo(Beer beer) {
        Snackbar.make(getBaseContentView(), getString(R.string.message_beer_added_collection, beer.getName()),
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadError(Throwable throwable) {
        /*
        Se passar aqui é porque as informações da cerveja não puderam ser carregadas. Mesmo assim
        a cerveja é adicionada a collection.
         */
        Snackbar.make(getBaseContentView(), getString(R.string.message_beer_added_collection, ""), Snackbar
                .LENGTH_SHORT).show();
    }

    @Override
    public void onTitleChanged(String title) {
        //Quando o DetailFragment está dentro da SearchActivity, ele não altera o título da Activity.

    }

    @Override
    public void onImageChanged(String imgUrl) {
        //Quando o DetailFragment está dentro da SearchActivity, ele não altera a imagem da toolbar.
    }
}
