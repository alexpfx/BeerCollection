package com.github.alexpfx.udacity.beercollection.collection.adapter;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    private final PublishSubject<View> detailEvent;

    private final PublishSubject<View> addBeerEvent;

    private final PublishSubject<View> toggleSelectionEvent;

    private final PublishSubject<View> toggleSelectionModeEvent;

    @BindView(R.id.image_beer_label)
    ImageView imageBeerLabel;

    @BindView(R.id.text_beer_name)
    TextView textBeerName;

    @BindView(R.id.text_last_drink_date)
    TextView textLastDrinkDate;

    @BindView(R.id.text_quantity)
    TextView textQuantity;

    @BindView(R.id.btn_drink_action)
    ImageButton btnDrink;

    @BindView(R.id.layout_collecion_item)
    ConstraintLayout layout;

    @BindView(R.id.view_clicable_area)
    View viewClicableArea;

    Target target = new LabelCollectionImageViewTarget(this);

    private Context context;


    public CollectionViewHolder(View itemView, PublishSubject<View> detailEvent, PublishSubject<View>
            addBeerEvent, PublishSubject<View> toggleSelectionEvent,
                                PublishSubject<View> toggleSelectionModeEvent) {
        super(itemView);
        this.detailEvent = detailEvent;
        this.addBeerEvent = addBeerEvent;
        this.toggleSelectionEvent = toggleSelectionEvent;
        this.toggleSelectionModeEvent = toggleSelectionModeEvent;


        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        setupEvents();
    }


    /**
     * Configura os eventos da UI collection_item, que são despachados
     * através de objetos PublishSubject do RxJava.
     */
    private void setupEvents() {
        RxView.clicks(btnDrink).map(a -> itemView).subscribe(addBeerEvent);

        CompositeClickListener compositeClickListener = new CompositeClickListener(itemView);
        compositeClickListener.registerListener(detailEvent::onNext);
        compositeClickListener.registerListener(toggleSelectionEvent::onNext);


        viewClicableArea.setOnClickListener(compositeClickListener);

        viewClicableArea.setOnLongClickListener(v -> {
            toggleSelectionModeEvent.onNext(itemView);
            return true;
        });
    }


    public void bind(CollectionItem item, boolean isSelected, boolean isSelectable) {
        viewClicableArea.setSelected(isSelected);
        imageBeerLabel.setClickable(!isSelectable);

        Beer beer = item.getBeer();
        String id = beer.getId();
        itemView.setTag(id);

        bindBeerLabel(beer);
        bindBeerName(beer);
        bindQuantity(item);
        bindLastDrinkDate(item);
    }


    private void bindBeerLabel(Beer beer) {
        int targetSize = Constants.COLLECTION_BEER_LABEL_IMAGE_SIZE;

        Picasso.with(context)
                .load(beer.getLabelLarge())
                .placeholder(R.drawable.beerplaceholder)
                .error(R.drawable.ic_warning_white)
                .resize(targetSize, targetSize)
                .transform(new CropSquareTransformation())
//                .transform(new CropMiddleFirstPixelTransformation())
                .centerCrop()
                .into(target);
    }


    private void bindBeerName(Beer beer) {
        textBeerName.setText(beer.getName());
        setTooltipText(textBeerName, R.string.tooltip_beer_name);
    }


    private void bindLastDrinkDate(CollectionItem collectionItem) {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
        CharSequence dateFormated = dateInstance.format(collectionItem.getLastDate());
        textLastDrinkDate.setText(TextUtils.concat(getString(R.string.icon_cmd_calendar), " ", dateFormated));
        setTooltipText(textLastDrinkDate, R.string.tooltip_last_beer);
    }


    private void bindQuantity(CollectionItem collectionItem) {
        textQuantity.setText(TextUtils.concat(getString(R.string.icon_cmd_beer), " ", String.valueOf(collectionItem
                .countBeers())));
        setTooltipText(textQuantity, R.string.tooltip_quantity);
    }


    private void setTooltipText(View view, int resId) {
        TooltipCompat.setTooltipText(view, getString(resId));
    }


    private String getString(@StringRes int id) {
        return context.getString(id);
    }


    public void onDestroy() {
    //    viewClicableArea.setOnClickListener(null);
    }
}




