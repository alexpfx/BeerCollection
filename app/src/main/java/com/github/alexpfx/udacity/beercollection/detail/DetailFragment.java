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
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment implements DetailView {

    private static final String TAG = "DetailFragment";

    @Inject
    DetailPresenter detailPresenter;
//    private DetailViewHolder detailViewHolder;


    private Listener listener;
    private DetailViewHolder detailViewHolder;



    public DetailFragment() {
        setHasOptionsMenu(true);
        // Required empty public constructor
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detailViewHolder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailViewHolder = new DetailViewHolder(view);


//        detailViewHolder = new DetailViewHolder(view);
//        executeOnActivityActionBar(ab -> ab.setDisplayHomeAsUpEnabled(true));
//        executeOnActivityActionBar(ab -> ab.setDisplayShowTitleEnabled(true));


//        detailViewHolder.getBeerClickObservable().subscribe(view -> {
//            Beer beer = (Beer) view.getTag();
//
//            DrinkBeerFragmentDialog.getInstance(beer.getId()).show(getSupportFragmentManager(),
// "DrinkBeerFragmentDialog");
//        });

        String beerId = getActivityIntent().getStringExtra(Constants.KEY_BEER_ID);
        detailPresenter.load(beerId);
        return view;

    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getDetailSubComponent(this).inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener){
            listener = (Listener) context;
        }
    }

    @Override
    public void showBeer(Beer beer) {
        listener.onTitleChanged(beer.getName());
        listener.onImageChanged(beer.getLabelLarge());


        detailViewHolder.setBeer(beer);

    }

    @Override
    public void showLoadError(Throwable throwable) {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: "+getActivity());
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        detailPresenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface Listener {
        void onTitleChanged(String title);
        void onImageChanged(String imgUrl);
    }


}
