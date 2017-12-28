package com.github.alexpfx.udacity.beercollection.collection;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MyCollectionFragment extends BaseFragment implements MyCollectionView, SwipeRefreshLayout
        .OnRefreshListener, DrinkBeerView {

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

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;




    private Unbinder unbinder;
    private DrinkBeerFragmentDialog.PositiveClickListener positiveClickListener;
    private StateListDrawable stateListDrawable;


    public MyCollectionFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            this.listener = (Listener) context;
        }
        stateListDrawable = (StateListDrawable) getResources().getDrawable(R.drawable.state_list_edit_mode);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
        this.positiveClickListener = null;
        stateListDrawable = null;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
        unbinder.unbind();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_my_collection, menu);
        setupSearchView(menu);
        showHideDeleteButton(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:
                toggleSelectionMode();
                item.setChecked(isSelectMode());
                stateListDrawable = (StateListDrawable) getResources().getDrawable(R.drawable.state_list_edit_mode);
                int[] state = {item.isChecked()?android.R.attr.state_checked:android.R.attr.state_empty};
                stateListDrawable.setState(state);
                getActivity().invalidateOptionsMenu();
                item.setIcon(stateListDrawable.getCurrent());
                break;
            case R.id.action_delete:
                deleteItems();
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isSelectMode (){
        Log.d(TAG, "isSelectMode: "+adapter.isSelectable());
        return adapter.isSelectable();
    }

    private void deleteItems() {
        if (!adapter.hasSelection()){
            // this will not be called, since if there is no selection, the delete button is not shown.
            return;
        }


    }


    private void showHideDeleteButton(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_delete);
        item.setVisible(adapter.isSelectable() && adapter.hasSelection());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        swipeRefreshCollection.setOnRefreshListener(this);

        rcvCollection.setAdapter(adapter);

        setClickListeners();

        executeOnActivityActionBar(ab -> ab.setDisplayHomeAsUpEnabled(false));

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.load();
    }

    //https://stackoverflow.com/questions/36497690/how-to-handle-item-clicks-for-a-recycler-view-using-rxjava
    private void setClickListeners() {
        compositeDisposable = new CompositeDisposable();
        Disposable disposable;

        disposable = adapter.getDetailViewClickObservable().subscribe(v -> navigateToDetail(v));
        compositeDisposable.add(disposable);

        disposable = adapter.getHistoryClickObservable().subscribe(
                v -> listener.navigateToHistory(getBeerIdFromTag(v))
                , onError -> {
                    //TODO
                    Log.e(TAG, "setClickListeners: ", onError);

                }
        );
        compositeDisposable.add(disposable);

        disposable = adapter.getAddBeerClickObservable().subscribe(v -> showDrinkDialog(getBeerIdFromTag(v)));
        compositeDisposable.add(disposable);

        disposable = adapter.getItemViewClickObservable().subscribe(this::toggleSelection);
        compositeDisposable.add(disposable);

    }

    private void navigateToDetail(View v) {
        Log.d(TAG, "navigateToDetail: ");
        listener.navigateToDetail
                (getBeerIdFromTag(v));
    }

    private void toggleSelectionMode() {
        boolean selectable = !adapter.isSelectable();
        adapter.setSelectable(selectable);
    }


    private void toggleSelection(View v) {
        Log.d(TAG, "toggleSelection: ");
        int position = rcvCollection.getChildAdapterPosition(v);
        boolean itemChecked = adapter.isItemChecked(position);
        getActivity().invalidateOptionsMenu();
        adapter.setItemChecked(position, !itemChecked);
    }


    private void showDrinkDialog(String id) {
        positiveClickListener = new DrinkBeerFragmentDialog
                .PositiveClickListener() {
            @Override
            public void onPositiveClick(Integer quant) {
                drinkBeerPresenter.drink(new DrinkBeerUpdateItem(id, quant));
            }
        };


        DrinkBeerFragmentDialog instance = DrinkBeerFragmentDialog.getInstance(id, positiveClickListener);
        instance.show(getActivity().getSupportFragmentManager(), "DrinkBeerDialogFragment");
    }

    private String getBeerIdFromTag(View v) {
        return (String) v.getTag();
    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent().inject(this);
        drinkBeerPresenter.bind(this);
        presenter.bind(this);
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
        if (swipeRefreshCollection != null) {
            swipeRefreshCollection.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (swipeRefreshCollection != null) {
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


    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.filter(query);
                return false;
            }
        });
    }

    @Override
    public void showDrinkAdded(String id, int quantity) {
        adapter.addTempItem(new CollectionItemVO(id, new Date().getTime(), quantity));
        if (quantity > 0) {
            Snackbar.make(swipeRefreshCollection, getString(R.string.message_you_drink_more_beers, quantity), Snackbar
                    .LENGTH_SHORT).show();
        }
    }


    @Override
    public void showError(Object error) {

    }

    public interface Listener {
        void navigateToDetail(String beerId);

        void navigateToHistory(String beerId);

//        void onAdd(String id);
    }


}
