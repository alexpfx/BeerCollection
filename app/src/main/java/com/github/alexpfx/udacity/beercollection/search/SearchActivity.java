package com.github.alexpfx.udacity.beercollection.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchView;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Adicionar indicador de loading
 */
public class SearchActivity extends BaseActivity implements SearchView {
    private static final String TAG = "SearchActivity";

    @BindView(R.id.rcv_search_result)
    RecyclerView rcvSearchResult;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @Inject
    SearchPresenter searchPresenter;



    @Inject
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        rcvSearchResult.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvSearchResult.setLayoutManager(layout);
        rcvSearchResult.addItemDecoration(new DividerItemDecoration(this, layout.getOrientation()));

        adapter.getClickDrinkObservable().subscribe(view -> {

            Beer beer = (Beer) view.getTag();
            Intent data = new Intent();
            data.putExtra(Constants.KEY_BEER_ID, beer.getId());
            data.putExtra(Constants.KEY_BEER_NAME, beer.getName());
            setResult(RESULT_OK, data);
            finish();
        });

        adapter.getViewClickObservable().subscribe(view -> {
            Beer beer = (Beer) view.getTag();

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(Constants.KEY_BEER_ID, beer.getId());
            intent.putExtra(Constants.KEY_BEER_NAME, beer.getName());
            startActivity(intent);

        });

    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent(this).inject(this);
    }

    @Override
    public void showSearchResult(@NonNull List<Beer> searchResult) {
        adapter.setItems(searchResult);
    }

    @Override
    public void showSearchError() {
        Log.e(TAG, "search error");
    }

    @Override
    public void showNoResults(String query) {
        Snackbar snack = Snackbar.make(findViewById(R.id.layout_search), getString(R.string.message_no_results) + " "
                        + query,
                Snackbar.LENGTH_LONG);
        snack.setAction(getString(R.string.action_dismiss), view -> snack.dismiss());
        snack.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearResults() {
        adapter.clear();
    }


    @OnClick(R.id.btn_action_search)
    public void actionSearch(View view) {
        if (!TextUtils.isEmpty(edtSearch.getText())) {
            searchPresenter.search(edtSearch.getText().toString());
        } else {
            edtSearch.requestFocus();
        }

    }

}
