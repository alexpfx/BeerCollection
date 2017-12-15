package com.github.alexpfx.udacity.beercollection.search;

import android.content.Context;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchView;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * Adicionar indicador de loading
 */
public class SearchActivity extends BaseActivity implements SearchView, DrinkBeerView {
    private static final String TAG = "SearchActivity";

    @BindView(R.id.rcv_search_result)
    RecyclerView rcvSearchResult;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @Inject
    SearchPresenter searchPresenter;

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

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


        adapter.getClickDetailViewObservable().subscribe(view -> {
            String beerId = String.valueOf(view.getTag());

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(Constants.KEY_BEER_ID, beerId);
            startActivity(intent);

        });

        adapter.getClickDownloadViewObservable().subscribe(view -> {
            String beerId = String.valueOf(view.getTag());
            drinkBeerPresenter.drink(new DrinkBeerUpdateItem(beerId, 0));
            finish();
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

    @OnEditorAction(R.id.edt_search)
    public boolean onEditorActionSearch(TextView tv, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            actionSearch(tv);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(tv.getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
            return true;
        }
        return false;
    }

    @OnClick(R.id.btn_action_search)
    public void actionSearch(View view) {
        if (!TextUtils.isEmpty(edtSearch.getText())) {
            searchPresenter.search(edtSearch.getText().toString());
        } else {
            edtSearch.requestFocus();
        }
    }


    @Override
    public void showDrinkAdded(int quantity) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void showError(Object error) {

    }
}
