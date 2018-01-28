package com.github.alexpfx.udacity.beercollection.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SearchFragment extends BaseFragment implements com.github.alexpfx.udacity.beercollection.databaselib
        .search.SearchView {

    @BindView(R.id.rcv_search_result)
    RecyclerView rcvSearchResult;
    @Inject
    SearchPresenter searchPresenter;
    @Inject
    SearchAdapter adapter;
    @BindView(R.id.edt_search_query)
    TextInputEditText edtSearchQuery;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Listener listener;
    private Unbinder unbinder;

    public SearchFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent().inject(this);
        searchPresenter.init(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);

        initializeRecyclerView();


        setupEvents();

        return view;
    }

    private void initializeRecyclerView() {
        rcvSearchResult.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvSearchResult.setLayoutManager(layout);
        rcvSearchResult.addItemDecoration(new DividerItemDecoration(getContext(), layout.getOrientation()));
    }

    private void setupEvents() {
        /*TODO: explicar */
        RxTextView.textChanges(edtSearchQuery)
                .filter(charSequence -> charSequence.length() >= 3)
                .debounce(Constants.QUERY_DEBONCE_TIME, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::performSearch);

        Disposable disposable = adapter.getClickDownloadViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag(R.id.tag_beer_id);
            String beerName = (String) view.getTag(R.id.tag_beer_name);

            listener.onDownload(beerId, beerName);

        });
        compositeDisposable.add(disposable);

        disposable = adapter.getClickDetailViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag(R.id.tag_beer_id);
            listener.onDetail(beerId);
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rcvSearchResult.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        searchPresenter.unLoad();
        compositeDisposable.dispose();
        listener = null;
    }

    @Override
    public void showSearchResult(List<Beer> searchResult) {
        adapter.setItems(searchResult);
    }

    @Override
    public void showSearchError() {
    }

    @Override
    public void showNoResults(String query) {
        //não mostra nada, pois com o Typeahead Search este método é chamado constantemente.
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

    @OnClick(R.id.button_search)
    public void onSearchClick(View view) {
        String query = edtSearchQuery.getText().toString();
        if (!query.isEmpty()) {
            performSearch(query);
        }
    }

    private void performSearch(String query) {
        searchPresenter.search(query);
    }

    public interface Listener {
        void onDetail(String id);

        void onDownload(String id, String name);


    }

}
