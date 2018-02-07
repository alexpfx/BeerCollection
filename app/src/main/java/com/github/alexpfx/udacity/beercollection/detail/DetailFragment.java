package com.github.alexpfx.udacity.beercollection.detail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenter;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenterView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import javax.inject.Inject;

public class DetailFragment extends BaseFragment implements LoadBeerInfoPresenterView {

    private static final String TAG = "DetailFragment";

    @Inject
    LoadBeerInfoPresenter loadBeerInfoPresenter;

    private Listener listener;

    private DetailViewHolder detailViewHolder;


    public DetailFragment() {
        setHasOptionsMenu(true);
    }


    public static DetailFragment getInstance(String beerId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(Constants.KEY_BEER_ID, beerId);
        detailFragment.setArguments(args);
        return detailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailViewHolder = new DetailViewHolder(view);
        loadBeerInfoPresenter.load(getBeerId());

        return view;
    }


    private String getBeerId() {
        if (getActivityIntent().hasExtra(Constants.KEY_BEER_ID)) {
            return getActivityIntent().getStringExtra(Constants.KEY_BEER_ID);
        } else {
            return getArguments().getString(Constants.KEY_BEER_ID);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detailViewHolder.unbind();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        loadBeerInfoPresenter.unLoad();
        listener = null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            listener = Listener.EMPTY;
        }
    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getDetailSubComponent().inject(this);
        loadBeerInfoPresenter.init(this);
    }


    @Override
    public void showBeerInfo(Beer beer) {

        listener.onTitleChanged(beer.getName());
        listener.onImageChanged(beer.getLabelLarge());

        detailViewHolder.setBeer(beer);
    }


    @Override
    public void showLoadError(Throwable throwable) {

    }


    public interface Listener {

        Listener EMPTY = new Listener() {
            @Override
            public void onTitleChanged(String title) {
                Log.d(TAG, "onTitleChanged: ");
            }


            @Override
            public void onImageChanged(String imgUrl) {
                Log.d(TAG, "onImageChanged: ");
            }
        };

        void onTitleChanged(String title);

        void onImageChanged(String imgUrl);
    }
}
