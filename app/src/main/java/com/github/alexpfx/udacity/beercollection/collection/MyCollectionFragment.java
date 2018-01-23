package com.github.alexpfx.udacity.beercollection.collection;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.drink.DrinkBeerFragmentDialog;
import com.github.alexpfx.udacity.beercollection.utils.Comparators;
import com.github.alexpfx.udacity.beercollection.utils.SelectableItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MyCollectionFragment extends BaseFragment implements MyCollectionView, SwipeRefreshLayout
        .OnRefreshListener, DrinkBeerView, DeleteBeerView {

    public static final String LAST_POSITION = "lastPosition";
    public static final String ADAPTER_KEY = "adapter_key";
    private static final String TAG = "MyCollectionFragment";
    @BindView(R.id.rcv_collection)
    RecyclerView rcvCollection;
    @BindView(R.id.swipe_refresh_collection)
    SwipeRefreshLayout swipeRefreshCollection;
    @BindView(R.id.text_empty_content)
    TextView txtMessagesEmptyCollection;
//    @Inject
//    CollectionAdapter adapter;

//    @Inject
//    CollectionMultiselectableAdapter adapter;

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
    private DrinkBeerFragmentDialog.PositiveClickListener positiveClickListener;
    private MenuItem.OnMenuItemClickListener actionDeleteClick = menuItem -> {
        List<String> selectedItemIds = getSelectedIds();
        deleteItems(selectedItemIds);
        return true;
    };
    private MenuItem.OnMenuItemClickListener actionCancelEditionClick = item -> {
        setSelectionMode(false);
        return true;
    };
    private SearchView searchView;
    public MyCollectionFragment() {

    }

    private List<String> getSelectedIds() {
        return adapter.get(SelectableItem::isSelected, selectableItem -> selectableItem.getItem().getBeer().getId());
    }

    private void deleteItems(List<String> selectedItemIds) {
        for (String selectedItemId : selectedItemIds) {
            delete(selectedItemId);
        }
    }

    private void delete(String beerId) {
        deleteBeerPresenter.deleteBeer(beerId);
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
        executeOnActivityActionBar(ab -> ab.setDisplayHomeAsUpEnabled(false));

        if (savedInstanceState != null) {
            lastPosition = savedInstanceState.getInt(LAST_POSITION);
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
        Parcelable state = savedInstanceState.getParcelable(ADAPTER_KEY);
//        adapter.onRestoreInstanceState(state);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putParcelable(ADAPTER_KEY, adapter.onSaveInstanceState());

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActivity().invalidateOptionsMenu();

        return true;
    }

    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
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

        disposable = adapter.getToggleSelectionModeEventObservable().subscribe(v -> toggleSelectionMode(v));
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
        Timber.d("toggleSelectionMode");
        setSelectionMode(!isSelectMode());
        toggleSelection(view);


    }

    private void setSelectionMode(boolean selectionMode) {
        adapter.setSelectable(selectionMode);
        getActivity().invalidateOptionsMenu();
    }

    private boolean isSelectMode() {
        return adapter.isSelectable();
    }

    private void toggleSelection(View v) {
        Timber.d("toggleSelection");
        int position = rcvCollection.getChildAdapterPosition(v);
        if (position == RecyclerView.NO_POSITION){
            return;
        }

        boolean itemChecked = adapter.isSelected(position);
        adapter.setSelected(position, !itemChecked);

    }

    private void navigateToDetail(View v) {
        Timber.d("navigateToDetail");
//        listener.navigateToDetail
//                (getBeerIdFromTag(v));
    }

    @Override
    public void showUserCollection(List<CollectionItem> items) {
        adapter.resetState(items);
        rcvCollection.setVisibility(View.VISIBLE);
        txtMessagesEmptyCollection.setVisibility(View.INVISIBLE);
        searchView.onActionViewCollapsed();

        getLinearLayoutManager(rcvCollection).scrollToPositionWithOffset(lastPosition, 0);
    }

    private LinearLayoutManager getLinearLayoutManager(RecyclerView recyclerView) {
        return (LinearLayoutManager) recyclerView.getLayoutManager();
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
        adapter.swapItems(new ArrayList<>());
    }

    @Override
    public void showDrinkAdded(String beerId, int quantity) {
//        adapter.addTempItem(new CollectionItemVO(beerId, new Date().getTime(), quantity));
        if (quantity > 0) {
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


}
