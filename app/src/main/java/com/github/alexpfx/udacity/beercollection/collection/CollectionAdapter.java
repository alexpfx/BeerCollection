package com.github.alexpfx.udacity.beercollection.collection;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
import com.github.alexpfx.udacity.beercollection.utils.Predicate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@PerActivity
public class CollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> implements Filter.FilterListener {

    private static final String TAG = "CollectionAdapter";
    private final PublishSubject<View> detailClickSubject = PublishSubject.create();
    private final PublishSubject<View> addBeerClickSubject = PublishSubject.create();
    private final PublishSubject<View> historyClickSubject = PublishSubject.create();
    private final PublishSubject<View> clickItemViewSubject = PublishSubject.create();
    private final PublishSubject<View> longClickItemViewSubject = PublishSubject.create();

    private List<CollectionItem> filteredItems;
    private List<CollectionItem> items = new ArrayList<>();

    private boolean selectable = false;
    private FilterBeer filterBeer;

    /*Use to discard view events if adapter state is in selection mode*/
    private io.reactivex.functions.Predicate<View> isNotSeletionMode = view -> !selectable;
    private io.reactivex.functions.Predicate<View> isSeletionMode = view -> selectable;
    private SelectableAdapter selectables = new SelectableItems();

    @Inject
    public CollectionAdapter() {
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(view, detailClickSubject, addBeerClickSubject, historyClickSubject,
                clickItemViewSubject, longClickItemViewSubject);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        CollectionItem item = filteredItems.get(position);
        holder.bind(item, selectables.isItemChecked(position), isSelectable());
    }

    @Override
    public int getItemCount() {
        return filteredItems == null ? 0 : filteredItems.size();
    }


    public void setItems(List<CollectionItem> items) {
        this.items = items;
        this.filteredItems = items;
        filterBeer = new FilterBeer(items);

        notifyDataSetChanged();
    }


    public List<String> getSelectedItemIds() {
        int i = 0;
        List<String> list = new ArrayList<>();
        for (CollectionItem item : items) {
            if (selectables.isItemChecked(i++)) {
                list.add(item.getBeer().getId());
            }
        }
        return list;
    }


    /**
     * Adds a temp CollectionItemVO to the beer item and allows user to see the change on quantity immediately
     *
     * @param collectionItemVO
     */
    public void addTempItem(CollectionItemVO collectionItemVO) {
        if (items == null) {
            return;
        }
        CollectionItem item = searchItemFromItemVo(collectionItemVO);
        if (item != null) {
            item.add(collectionItemVO);
            setItems(items);
        }
    }

    private CollectionItem searchItemFromItemVo(CollectionItemVO collectionItemVO) {
        for (CollectionItem item : items) {
            if (item.getBeer().getId().equals(collectionItemVO.getBeerId())) {
                return item;
            }
        }
        return null;
    }

    public void clear() {
        items = null;
        selectables.clearSelectedItems();
        notifyDataSetChanged();
    }

    public void filter(String query) {
        if (filterBeer == null) {
            return;
        }
        filterBeer.filter(query, this);
    }

    @Override
    public void onFilterComplete(int i) {
        filteredItems = filterBeer.getFilteredItems();
        notifyDataSetChanged();
    }

    public void setItemChecked(int position, boolean isChecked) {
        selectables.setItemChecked(position, isChecked);
        notifyItemChanged(position);
    }

    public boolean isItemChecked(int position) {
        return selectables.isItemChecked(position);
    }

    public boolean isSelectable() {
        return selectable;
    }


    public void setSelectable(boolean selectable) {
        this.selectable = selectable;

    }

    public boolean hasSelection() {
        return selectables.hasSelection();
    }

    public void clearSelectedItems() {
        selectables.clearSelectedItems();
    }

    public Parcelable onSaveInstanceState() {
        return selectables;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        this.selectables = (SelectableAdapter) parcelable;

    }

    public Observable<View> getDetailViewClickObservable() {
        return detailClickSubject
                /*Discard view events if adapter state is in selection mode*/
//                .filter(isNotSeletionMode)
                .map(v -> {
                    if (selectable) {
                        clickItemViewSubject.onNext((View) v.getParent());
                    }
                    return v;
                })
                .filter(isNotSeletionMode)
                .hide();
    }

    public Observable<View> getAddBeerClickObservable() {
        return addBeerClickSubject
                .filter(isNotSeletionMode)
                .hide();
    }

    public Observable<View> getHistoryClickObservable() {
        return historyClickSubject
                .filter(isNotSeletionMode)
                .hide();
    }

    public Observable<View> getItemViewClickObservable() {
        return clickItemViewSubject
                .filter(isSeletionMode)
                .hide();
    }

    public Observable<View> getLongClickViewObservable() {
        return longClickItemViewSubject.hide();
    }


    private static class FilterBeer extends Filter {

        private List<CollectionItem> filteredItems;
        private List<CollectionItem> items;

        public FilterBeer(List<CollectionItem> items) {
            this.items = items;
        }

        private List<CollectionItem> filter(Predicate<CollectionItem> predicate) {
            List<CollectionItem> filtered = new ArrayList<>();
            if (items != null) {
                for (CollectionItem item : items) {
                    if (predicate.test(item)) {
                        filtered.add(item);
                    }
                }
            }
            return filtered;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchTerm = charSequence.toString().toLowerCase();
            List<CollectionItem> items = filter(item -> item.getBeer().getName()
                    .toLowerCase().contains(searchTerm) || searchTerm.isEmpty());
            return createResults(items);
        }

        FilterResults createResults(List<CollectionItem> items) {
            FilterResults filterResults = new FilterResults();
            filterResults.values = items;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredItems = (List<CollectionItem>) filterResults.values;
        }

        public List<CollectionItem> getFilteredItems() {
            return filteredItems;
        }
    }


}




