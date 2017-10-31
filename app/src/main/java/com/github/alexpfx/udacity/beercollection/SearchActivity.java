package com.github.alexpfx.udacity.beercollection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.alexpfx.udacity.beercollection.beer.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchView;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchView {
    private static final String TAG = "SearchActivity";

    @BindView(R.id.rcv_search_result)
    RecyclerView rcvSearchResult;


    @BindView(R.id.edt_search)
    EditText edtSearch;

    @Inject
    SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent(this).inject(this);
    }

    @Override
    public void showSearchResult(@NonNull List<Beer> searchResult) {
        for (Beer beer : searchResult) {

        }

    }

    @Override
    public void showSearchError(Throwable throwable) {
        Log.e(TAG, "search errors: " + throwable.getMessage(), throwable);
    }

    @Override
    public void showNoResults(String query) {
        Log.d(TAG, "there is no beer result for " + query);
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
