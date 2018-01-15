package com.github.alexpfx.udacity.beercollection.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SearchFragment extends BaseFragment implements com.github.alexpfx.udacity.beercollection.databaselib
        .search.SearchView {


    private static final String TAG = "SearchFragment";
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
        Disposable disposable = adapter.getClickDownloadViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag(R.id.tag_beer_id);
            String beerName = (String) view.getTag(R.id.tag_beer_name);

            listener.onDownload(beerId, beerName);

        });
        compositeDisposable.add(disposable);

        disposable = adapter.getClickDetailViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag();
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
        Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.layout_search), getString(R.string
                        .message_no_results) + " "
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

    @OnClick(R.id.button_search)
    public void onSearchClick(View view) {
        String query = edtSearchQuery.getText().toString();
        if (!query.isEmpty()) {
            searchPresenter.search(query);
        }
    }

    public interface Listener {
        void onDetail(String id);

        void onDownload(String id, String name);


    }
//
//            adapter.getClickDetailViewObservable().subscribe(view -> {
//        String beerId = String.valueOf(view.getTag());
//
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(Constants.KEY_BEER_ID, beerId);
//        startActivity(intent);
//
//    });
//
//        adapter.getClickDownloadViewObservable().subscribe(view -> {
//        String beerId = String.valueOf(view.getTag());
//        drinkBeerPresenter.drink(new DrinkBeerUpdateItem(beerId, 0));
//        finish();
//    });


}
