package com.github.alexpfx.udacity.beercollection.collection;

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

/**
 * Created by alexandre on 10/11/17.
 */
@PerActivity
public class CollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> implements Filter.FilterListener {


    private final PublishSubject<View> clickDetailSubject = PublishSubject.create();
    private final PublishSubject<View> clickAddBeerSubject = PublishSubject.create();
    private final PublishSubject<View> clickHistorySubject = PublishSubject.create();
    private List<CollectionItem> filteredItems;
    private List<CollectionItem> items = new ArrayList<>();

    private FilterBeer filterBeer;


    @Inject
    public CollectionAdapter() {
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(view, clickDetailSubject, clickAddBeerSubject, clickHistorySubject);
    }


    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        holder.bind(filteredItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItems == null ? 0 : filteredItems.size();
    }


    public Observable<View> getDetailClickSubject() {
        return clickDetailSubject.hide();
    }

    public Observable<View> getClickAddBeerSubject() {
        return clickAddBeerSubject.hide();
    }

    public Observable<View> getClickHistorySubject() {
        return clickHistorySubject.hide();
    }

    public void setItems(List<CollectionItem> items) {
        this.items = items;
        this.filteredItems = items;
        filterBeer = new FilterBeer(items);
        notifyDataSetChanged();
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
        notifyDataSetChanged();
    }


    public synchronized void filter(String query) {
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




