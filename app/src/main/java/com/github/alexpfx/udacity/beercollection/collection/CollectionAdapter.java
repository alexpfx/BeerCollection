package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.ClickObservableBaseAdapter;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionScope;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.CollectionItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alexandre on 10/11/17.
 */
@MyCollectionScope
public class CollectionAdapter extends ClickObservableBaseAdapter<CollectionAdapter.CollectionViewHolder,
        CollectionItem> {

    @Inject
    public CollectionAdapter() {
    }

    @Override
    protected CollectionViewHolder createViewHolder(View view) {
        return new CollectionViewHolder(view);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_collection, parent, false);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_beer_label)
        ImageView imageBeerLabel;
        @BindView(R.id.text_beer_name)
        TextView textBeerName;
        @BindView(R.id.text_last_drink_date)
        TextView textLastDrinkDate;
        @BindView(R.id.text_quantity)
        TextView textQuantity;
        private Context context;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
        }

        public void bind(CollectionItem collectionItem) {
            Beer beer = collectionItem.getBeer();
            Picasso.with(context).load(beer.getLabelLarge()).into(imageBeerLabel);

            textBeerName.setText(beer.getName());
            textQuantity.setText("10");
            textLastDrinkDate.setText("12/12/2017");
        }


    }


}




