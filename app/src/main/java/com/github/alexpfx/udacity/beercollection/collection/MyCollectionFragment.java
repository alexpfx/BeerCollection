package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyCollectionFragment extends BaseFragment implements MyCollectionView, SwipeRefreshLayout
        .OnRefreshListener{

    private static final String TAG = "MyCollectionFragment";
    @BindView(R.id.rcv_collection)
    RecyclerView rcvCollection;

    @BindView(R.id.swipe_refresh_collection)
    SwipeRefreshLayout swipeRefreshCollection;

    @BindView(R.id.text_empty_content)
    TextView txtMessagesEmptyCollection;

    @Inject
    CollectionAdapter adapter;

    CompositeDisposable compositeDisposable;

    Listener listener;

    @Inject
    LoadCollectionPresenter presenter;

    private Unbinder unbinder;


    public MyCollectionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            this.listener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        swipeRefreshCollection.setOnRefreshListener(this);

        rcvCollection.setAdapter(adapter);

        setClickListeners ();

        executeOnActivityActionBar(ab -> ab.setDisplayHomeAsUpEnabled(true));

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.load();
    }

    //https://stackoverflow.com/questions/36497690/how-to-handle-item-clicks-for-a-recycler-view-using-rxjava
    private void setClickListeners() {
        Disposable disposable = adapter.getDetailClickSubject().subscribe(v -> listener.onDetail(getBeerIdFromTag(v)));

        compositeDisposable = new CompositeDisposable();

        if (compositeDisposable.isDisposed()){
            Log.d(TAG, "setClickListeners: ");
        }
        compositeDisposable.add(disposable);

        disposable = adapter.getClickHistorySubject().subscribe(
                v -> listener.onHistory(getBeerIdFromTag(v))
                , onError -> {
                    Log.e(TAG, "setClickListeners: ", onError);

                }
        );

        compositeDisposable.add(disposable);

        disposable = adapter.getClickAddBeerSubject().subscribe(v -> listener.onAdd(getBeerIdFromTag(v)));

        compositeDisposable.add(disposable);


    }

    private String getBeerIdFromTag(View v) {
        return (String) v.getTag();
    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent(getActivity(), this).inject(this);
    }

    @Override
    public void showUserCollection(List<CollectionItem> items) {
        rcvCollection.setVisibility(View.VISIBLE);
        txtMessagesEmptyCollection.setVisibility(View.INVISIBLE);

        adapter.setItems(items);
    }

    @Override
    public void showErrorLoadingCollection(String message) {
        rcvCollection.setVisibility(View.INVISIBLE);
        txtMessagesEmptyCollection.setVisibility(View.VISIBLE);
        txtMessagesEmptyCollection.setText(getString(R.string.message_content_cannot_loaded));
    }

    @Override
    public void showCollectionEmpty() {
        rcvCollection.setVisibility(View.INVISIBLE);
        txtMessagesEmptyCollection.setVisibility(View.VISIBLE);
        txtMessagesEmptyCollection.setText(getString(R.string.message_no_itens_in_collection));
    }


    @Override
    public void showLoading() {
        if (swipeRefreshCollection != null){
            swipeRefreshCollection.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (swipeRefreshCollection != null){
            swipeRefreshCollection.setRefreshing(false);
        }

    }

    @Override
    public void clearResults() {
        adapter.clear();
    }

    @Override
    public void onRefresh() {
        presenter.load();
    }



    public interface Listener {
        void onDetail (String beerId);
        void onHistory (String beerId);
        void onAdd (String id);
    }

}
