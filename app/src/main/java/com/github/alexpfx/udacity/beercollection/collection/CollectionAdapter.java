package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.ClickObservableBaseAdapter;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 10/11/17.
 */
@PerActivity
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
        private DateFormat dateFormat = new DateFormat();

        public CollectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(CollectionItem collectionItemVO) {
            Beer beer = collectionItemVO.getBeer();

            Picasso.with(context).load(beer.getLabelLarge()).resize(320,320).placeholder(R.drawable.beerplaceholder).centerCrop().into(imageBeerLabel);

            textBeerName.setText(beer.getName());
            String labelQuantity = String.valueOf(context.getString(R.string.label_quantity));
            textQuantity.setText(TextUtils.concat(labelQuantity, ": ", String.valueOf(collectionItemVO.countBeers())));

            CharSequence dateFormated = DateFormat.format(context.getString(R.string.date_format), collectionItemVO
                    .getLastDate());

            textLastDrinkDate.setText(
                    TextUtils.concat(getString(R.string.label_last_beer), ": ", dateFormated));


        }

        private String getString(@StringRes int id) {
            return context.getString(id);
        }


    }


}




