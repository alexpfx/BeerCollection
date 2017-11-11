package com.github.alexpfx.udacity.beercollection.collection;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.domain.model.local.CollectionItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyCollectionFragment extends BaseFragment implements MyCollectionView {

    private static final String TAG = "MyCollectionFragment";
    @BindView(R.id.rcv_collection)
    RecyclerView rcvCollection;
    @Inject
    CollectionAdapter adapter;
    @Inject
    MyCollectionPresenter presenter;

    public MyCollectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collection, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
//            presenter.bind(this);
        }

//        presenter.load();

        rcvCollection.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        return view;

    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent().inject(this);
    }

    @Override
    public void showUserCollection(List<CollectionItem> items) {
        Log.d(TAG, "showUserCollection: ");

    }

    @Override
    public void showErrorLoadingCollection() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLodading() {

    }

    @Override
    public void clearResults() {

    }
}
