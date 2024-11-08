package com.github.alexpfx.udacity.beercollection.collection;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.github.alexpfx.udacity.beercollection.Constants.Keys;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.collection.adapter.SelectableItemsAdapter;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
import com.github.alexpfx.udacity.beercollection.drink.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.utils.Comparators;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MyCollectionFragment extends BaseFragment implements MyCollectionView, SwipeRefreshLayout
        .OnRefreshListener, DrinkBeerView, DeleteBeerView {



    @BindView(R.id.rcv_collection)
    RecyclerView rcvCollection;

    @BindView(R.id.swipe_refresh_collection)
    SwipeRefreshLayout swipeRefreshCollection;

    @BindView(R.id.text_empty_content)
    TextView txtMessagesEmptyCollection;

    @Inject
    SelectableItemsAdapter adapter;

    CompositeDisposable compositeDisposable;

    Listener listener;

    @Inject
    LoadCollectionPresenter loadCollectionPresenter;

    @Inject
    DrinkBeerPresenter drinkBeerPresenter;

    @Inject
    DeleteBeerPresenter deleteBeerPresenter;

    private int lastPosition = 0;

    private Unbinder unbinder;

    private String searchQuery;

    private DrinkBeerFragmentDialog.PositiveClickListener positiveClickListener;

    private MenuItem.OnMenuItemClickListener actionDeleteClick = menuItem -> {
        List<String> selectedItemIds = adapter.getSelectedIds();
        deleteItems(selectedItemIds);
        return true;
    };

    private MenuItem.OnMenuItemClickListener actionCancelEditionClick = item -> {
        setSelectionMode(false);
        return true;
    };

    private SearchView searchView;

    private Bundle adapterState;


    public MyCollectionFragment() {
        setRetainInstance(true);
    }


    private void deleteItems(List<String> selectedItemIds) {
        deleteBeerPresenter.deleteBeers(selectedItemIds);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        compositeDisposable = new CompositeDisposable();
        if (context instanceof Listener) {
            this.listener = (Listener) context;
        }
    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent().inject(this);
        drinkBeerPresenter.init(this);
        deleteBeerPresenter.init(this);
        loadCollectionPresenter.init(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collection, container, false);
        unbinder = ButterKnife.bind(this, view);

        swipeRefreshCollection.setOnRefreshListener(this);

        setupRecycler();
        setupEvents();

        if (savedInstanceState != null) {
            lastPosition = savedInstanceState.getInt(Keys.LAST_LIST_POSITION);
        }

        loadCollectionPresenter.load(Comparators.COLLECTION_ITEM_BY_DATE_DESC);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        getLinearLayoutManager().scrollToPositionWithOffset(lastPosition, 0);
        Bundle adapterState = savedInstanceState.getBundle(Keys.ADAPTER);

        searchQuery = savedInstanceState.getString(Keys.SEARCH_QUERY);
        this.adapterState = adapterState;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(Keys.LAST_LIST_POSITION, getLinearLayoutManager().findFirstVisibleItemPosition());
        Bundle adapterBundle = adapter.onSaveInstanceState();
        outState.putBundle(Keys.ADAPTER, adapterBundle);
        outState.putString(Keys.SEARCH_QUERY, searchView.getQuery().toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rcvCollection.setAdapter(null);
        loadCollectionPresenter.unLoad();

        unbinder.unbind();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        setSelectionMode(false);
        this.listener = null;
        this.positiveClickListener = null;

        compositeDisposable.dispose();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_my_collection, menu);
        setupSearchView(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        setupEditMode(menu);
    }


    private void setupEditMode(Menu menu) {
        boolean visible = isSelectMode();

        MenuItem action_cancel_edition = menu.findItem(R.id.action_cancel_edit);
        action_cancel_edition.setVisible(visible);
        action_cancel_edition.setOnMenuItemClickListener(actionCancelEditionClick);

        MenuItem action_delete = menu.findItem(R.id.action_delete);
        action_delete.setOnMenuItemClickListener(actionDeleteClick);
        action_delete.setVisible(visible);
    }


    private boolean isSelectMode() {
        return adapter.isSelectable();
    }


    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);


        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));


        searchView.setOnQueryTextListener(new OnQueryTextListener());


        if (!TextUtils.isEmpty(searchQuery)) {
            searchView.onActionViewExpanded();
            searchView.setQuery(searchQuery, true);
            searchView.clearFocus();
        }
    }


    private void setSelectionMode(boolean selectionMode) {
        adapter.setSelectable(selectionMode);

        if (!selectionMode) {
            adapter.clearSelections();
        }

        getActivity().invalidateOptionsMenu();
    }


    private LinearLayoutManager getLinearLayoutManager() {
        return (LinearLayoutManager) rcvCollection.getLayoutManager();
    }


    private void setupRecycler() {
        rcvCollection.setAdapter(adapter);
    }


    //https://stackoverflow.com/questions/36497690/how-to-handle-item-clicks-for-a-recycler-view-using-rxjava
    private void setupEvents() {
        Disposable disposable;

        disposable = adapter.getDetailEventObservable().subscribe(this::navigateToDetail);
        compositeDisposable.add(disposable);

        disposable = adapter.getAddBeerEventObservable().subscribe(v -> showDrinkDialog(getBeerIdFromTag(v)));
        compositeDisposable.add(disposable);


        disposable = adapter.getToggleSelectionEventObservable().subscribe(this::toggleSelection);
        compositeDisposable.add(disposable);

        disposable = adapter.getToggleSelectionModeEventObservable().subscribe(this::toggleSelectionMode);
        compositeDisposable.add(disposable);
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


    private void toggleSelectionMode(View view) {
        setSelectionMode(!isSelectMode());
        if (isSelectMode()) {
            toggleSelection(view);
        }
    }


    private void toggleSelection(View v) {
        int position = rcvCollection.getChildAdapterPosition(v);
        if (position == RecyclerView.NO_POSITION) {
            return;
        }

        boolean itemChecked = adapter.isSelected(position);
        adapter.setSelected(position, !itemChecked);
    }


    private void navigateToDetail(View v) {
        listener.navigateToDetail
                (getBeerIdFromTag(v));
    }


    @Override
    public void showUserCollection(List<CollectionItem> items) {
        adapter.resetState(items);
        rcvCollection.setVisibility(View.VISIBLE);
        txtMessagesEmptyCollection.setVisibility(View.INVISIBLE);
        searchView.onActionViewCollapsed();


        getLinearLayoutManager().scrollToPositionWithOffset(lastPosition, 0);

        if (adapterState != null) {
            adapter.onRestoreInstanceState(adapterState);
            getActivity().invalidateOptionsMenu();
            adapterState = null;
        }
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
    public void showDrinkAdded(String beerId, int quantity) {
        if (quantity > 0) {
            adapter.addTempItem(new CollectionItemVO(beerId, new Date().getTime(), quantity));
            Snackbar.make(swipeRefreshCollection, getResources().getQuantityString(R.plurals
                    .message_you_drink_more_beers, quantity, quantity), Snackbar
                    .LENGTH_SHORT).show();
        }
    }


    @Override
    public void showErrorOnDrinkBeer(Object error) {

    }


    @Override
    public void showBeerDeleted(String beerId) {

    }


    @Override
    public void showBeersDeleted() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        loadCollectionPresenter.load(Comparators.COLLECTION_ITEM_BY_DATE_DESC);
    }


    @Override
    public void showCouldNotDeleteBeer() {

    }


    public interface Listener {

        void navigateToDetail(String beerId);
    }

    private class OnQueryTextListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }


        @Override
        public boolean onQueryTextChange(String query) {
            adapter.getFilter().filter(query);

            return true;
        }
    }
}
