package com.github.alexpfx.udacity.beercollection.search;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements com.github.alexpfx.udacity.beercollection.databaselib
        .search.SearchView {


    @BindView(R.id.rcv_search_result)
    RecyclerView rcvSearchResult;
    @Inject
    SearchPresenter searchPresenter;
    @Inject
    SearchAdapter adapter;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Listener listener;


    private Unbinder unbinder;

    public SearchFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        setupSearchView(menu);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }


    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchPresenter.search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

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

    private void setupEvents() {
        Disposable disposable = adapter.getClickDownloadViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag();
            listener.onDownload(beerId);

        });
        compositeDisposable.add(disposable);

        disposable = adapter.getClickDetailViewObservable().subscribe(view -> {
            String beerId = (String) view.getTag();
            listener.onDetail(beerId);
        });
        compositeDisposable.add(disposable);
    }



    private void initializeRecyclerView() {
        rcvSearchResult.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvSearchResult.setLayoutManager(layout);
        rcvSearchResult.addItemDecoration(new DividerItemDecoration(getContext(), layout.getOrientation()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
        unbinder.unbind();
        searchPresenter.onDestroy();
        adapter = null;
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent().inject(this);
        searchPresenter.bind(this);

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

    public interface Listener {
        void onDetail(String id);
        void onDownload (String id);


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
