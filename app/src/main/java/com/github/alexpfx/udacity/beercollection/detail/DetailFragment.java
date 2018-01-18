package com.github.alexpfx.udacity.beercollection.detail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment implements LoadBeerInfoPresenterView {

    private static final String TAG = "DetailFragment";

    @Inject
    LoadBeerInfoPresenter loadBeerInfoPresenter;


    private Listener listener;
    private DetailViewHolder detailViewHolder;


    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailViewHolder = new DetailViewHolder(view);

        String beerId = getActivityIntent().getStringExtra(Constants.KEY_BEER_ID);

        Log.d(TAG, "onCreateView: "+beerId);
        loadBeerInfoPresenter.load(beerId);
        return view;

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
                Log.d(TAG, "onOptionsItemSelected: " + getActivity());
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
        void onTitleChanged(String title);

        void onImageChanged(String imgUrl);
    }


}
