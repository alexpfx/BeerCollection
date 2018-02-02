package com.github.alexpfx.udacity.beercollection.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
import com.github.alexpfx.udacity.beercollection.utils.FilterUtils;
import com.github.alexpfx.udacity.beercollection.utils.Function;
import com.github.alexpfx.udacity.beercollection.utils.Predicate;
import com.github.alexpfx.udacity.beercollection.utils.SelectableItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@PerActivity
public class SelectableItemsAdapter extends RecyclerView.Adapter<CollectionViewHolder> implements
        MultiSelectableAdapter, Filterable {

    public static final String SELECTED_KEY = "selectables";

    public static final String IS_SELECTABLE_KEY = "IS_SELECTABLE";

    private static final String TAG = "SelectableItemsAdapter";

    private final PublishSubject<View> detailEvent = PublishSubject.create();

    private final PublishSubject<View> addBeerEvent = PublishSubject.create();

    private final PublishSubject<View> toggleSelectionEvent = PublishSubject.create();

    private final PublishSubject<View> toggleSelectionModeEvent = PublishSubject.create();

    private final List<SelectableItem<CollectionItem>> items = new ArrayList<>();

    private final List<SelectableItem<CollectionItem>> fullItems = new ArrayList<>();


    private boolean selectable = false;

    private io.reactivex.functions.Predicate<View> isNotSeletionMode = view -> !selectable;

    private io.reactivex.functions.Predicate<View> isSeletionMode = view -> selectable;

    private SparseBooleanArrayParcelable sparseBooleanArray;


    @Inject
    public SelectableItemsAdapter() {
    }


    @Override
    public boolean isSelectable() {
        return selectable;
    }


    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }


    @Override
    public boolean isSelected(int position) {
        return position != RecyclerView.NO_POSITION && items.get(position).isSelected();
    }


    @Override
    public void setSelected(int position, boolean isSelected) {
        items.get(position).setSelected(isSelected);
        notifyItemChanged(position);
    }


    @Override
    public void clearSelections() {
        get(SelectableItem::isSelected, selectableItem -> {
            int position = items.indexOf(selectableItem);
            selectableItem.setSelected(false);
            notifyItemChanged(position);
            return selectableItem;
        });
    }

    /**
     * Método utilizado para realizar queries de propósito geral sobre os Items do adaspter.
     *
     * @param <RT>      Tipo de retorno da função
     * @param predicate Usado para filtrar os itens.
     * @param function  Recebe uma função que pode ser usada para aplicar uma transformação
     *                  no item filtrado antes de que ele seja retornado.
     * @return A lista filtrada pela query
     */
    private <RT> List<RT> get(Predicate<SelectableItem<CollectionItem>>
                                      predicate, Function<SelectableItem<CollectionItem>, RT> function) {
        return (get(predicate, function, null));
    }

    private <RT> List<RT> get(Predicate<SelectableItem<CollectionItem>>
                                      predicate, Function<SelectableItem<CollectionItem>, RT> function,
                              @Nullable Predicate<SelectableItem<CollectionItem>> stopCondition) {
        return FilterUtils.filter(items, predicate, function, stopCondition);
    }


    private <RT> List<RT> get(List<SelectableItem<CollectionItem>> inputList, Predicate<SelectableItem<CollectionItem>>
                                      predicate, Function<SelectableItem<CollectionItem>, RT> function) {
        return FilterUtils.filter(inputList, predicate, function, null);

    }




    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);

        return new CollectionViewHolder(view, detailEvent, addBeerEvent,
                toggleSelectionEvent, toggleSelectionModeEvent);
    }


    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        SelectableItem<CollectionItem> item = items.get(position);
        holder.bind(item.getItem(), item.isSelected(), selectable);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void resetState(List<CollectionItem> newItems) {
        List<SelectableItem<CollectionItem>> sNewItems = new ArrayList<>();
        setSelectable(false);
        for (CollectionItem newItem : newItems) {
            sNewItems.add(SelectableItem.createFrom(newItem));
        }
        swapItems(sNewItems);
    }


    public void swapItems(List<SelectableItem<CollectionItem>> newItems) {
        fullItems.clear();
        fullItems.addAll(newItems);
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }


    public Observable<View> getDetailEventObservable() {
        return detailEvent
                /*Discard view events if adapter state is in selection mode*/
                .filter(isNotSeletionMode)
                .hide();
    }


    public Observable<View> getAddBeerEventObservable() {
        return addBeerEvent
                .hide();
    }


    public Observable<View> getToggleSelectionEventObservable() {
        return toggleSelectionEvent
                .filter(isSeletionMode)
                .hide();
    }


    public Observable<View> getToggleSelectionModeEventObservable() {
        return toggleSelectionModeEvent.hide();
    }


    public List<String> getSelectedIds() {
        return get(SelectableItem::isSelected, selectableItem -> selectableItem.getItem().getBeer().getId());
    }


    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_SELECTABLE_KEY, selectable);
        if (!selectable) {
            return bundle;
        }

        List<Integer> selectedItemsIndex = get(item -> item.isSelected(), items::indexOf);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (Integer itemIndex : selectedItemsIndex) {
            sparseBooleanArray.put(itemIndex, true);
        }
        bundle.putParcelable(SELECTED_KEY, new SparseBooleanArrayParcelable(sparseBooleanArray));
        return bundle;
    }


    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        selectable = bundle.getBoolean(IS_SELECTABLE_KEY);

        if (!selectable) {
            return;
        }
        sparseBooleanArray = bundle.getParcelable(SELECTED_KEY);
        for (int i = 0; i < items.size(); i++) {
            SelectableItem<CollectionItem> collectionItemSelectableItem = items.get(i);
            collectionItemSelectableItem.setSelected(sparseBooleanArray.getSparseBooleanArray().get(i));
        }
    }


    /**
     * Adiciona um CollectionItemVO provisório para que a contagem de cervejas tomadas para aquele item
     * seja atualizada antes que fazer uma nova consulta ao banco de dados.
     *
     * @param collectionItemVO
     */
    public void addTempItem(CollectionItemVO collectionItemVO) {
        String beerId = collectionItemVO.getBeerId();
        Predicate<SelectableItem<CollectionItem>> predicate = item -> item.getItem().getBeer().getId()
                .equals(beerId);
        List<CollectionItem> items = get(predicate, SelectableItem::getItem, predicate);
        if (!items.isEmpty()) {
            CollectionItem collectionItem = items.get(0);
            collectionItem.add(collectionItemVO);
        }
        //TODO otimizar notificando apenas a position.
        notifyDataSetChanged();
    }




    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String queryString = constraint.toString().toLowerCase();
                List<SelectableItem<CollectionItem>> resultList;

                if (queryString.isEmpty()) {
                    resultList = fullItems;
                } else {
                    resultList = get(fullItems, sItem -> sItem.getItem().getBeer().getName().toLowerCase().contains(constraint), rItem -> rItem);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resultList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results == null) {
                    return;
                }
                Object values = results.values;
                if (values.equals(items)){
                    return;
                }

                items.clear();
                items.addAll((Collection<? extends SelectableItem<CollectionItem>>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
