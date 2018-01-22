package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import io.reactivex.subjects.PublishSubject;

public class CollectionViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CollectionViewHolder";
    private final PublishSubject<View> clickDetailSubject;
    private final PublishSubject<View> clickAddBeerSubject;
    private final PublishSubject<View> clickToggleSelectionSubject;
    private final PublishSubject<View> longClickItemViewSubject;

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
    @BindView(R.id.view_scrim)
    View viewScrim;

    @BindView(R.id.btn_toggle_selection)
    ImageButton btnToggleSelection;

    private Context context;


    public CollectionViewHolder(View itemView, PublishSubject<View> clickDetailSubject, PublishSubject<View>
            clickAddBeerSubject, PublishSubject<View>
            clickToggleSelectionSubject,
                                PublishSubject<View> longClickItemViewSubject
    ) {
        super(itemView);
        this.clickDetailSubject = clickDetailSubject;
        this.clickAddBeerSubject = clickAddBeerSubject;
        this.clickToggleSelectionSubject = clickToggleSelectionSubject;
        this.longClickItemViewSubject = longClickItemViewSubject;


        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        setupEvents();
    }

    private void setupEvents() {

        //RxView.clicks(viewOverlay).map(a -> itemView).subscribe(clickToggleSelectionSubject);

        RxView.clicks(imageBeerLabel).map(a -> itemView).subscribe(clickDetailSubject);
        RxView.clicks(btnToggleSelection).map(a -> itemView).subscribe(clickToggleSelectionSubject);
        RxView.clicks(btnDrink).map(a -> itemView).subscribe(clickAddBeerSubject);

    }

    public void bind(CollectionItem item, boolean isSelected, boolean isSelectable) {
        btnToggleSelection.setSelected(isSelected);
        btnToggleSelection.setVisibility(isSelectable ? View.VISIBLE : View.INVISIBLE);
        imageBeerLabel.setClickable(!isSelectable);

        Beer beer = item.getBeer();
        String id = beer.getId();
        itemView.setTag(id);

        bindBeerLabel(beer);
        bindBeerName(beer);
        bindQuantity(item);
        bindLastDrinkDate(item);
    }

    @OnLongClick(R.id.image_beer_label)
    public boolean onImageBeerLabelLongClick(View view) {
        longClickItemViewSubject.onNext(itemView);
        return true;
    }

    private void bindBeerLabel(Beer beer) {

        int targetHeight = 320;
        Picasso.with(context)
                .load(beer.getLabelLarge())
                .placeholder(R.drawable.beerplaceholder)
                .error(R.drawable.ic_warning_white)
                .resize(targetHeight, targetHeight)
//                .transform(new CropMiddleFirstPixelTransformation())
                .centerCrop()
                .into(imageBeerLabel);

    }


    private void bindBeerName(Beer beer) {
        textBeerName.setText(beer.getName());
        setTooltipText(textBeerName, R.string.tooltip_beer_name);

    }


    private void bindLastDrinkDate(CollectionItem collectionItem) {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
        CharSequence dateFormated = dateInstance.format(collectionItem.getLastDate());
        textLastDrinkDate.setText(dateFormated);

        setTooltipText(textLastDrinkDate, R.string.tooltip_last_beer);


    }

    private void bindQuantity(CollectionItem collectionItem) {
        textQuantity.setText(String.valueOf(collectionItem.countBeers()));
        setTooltipText(textQuantity, R.string.tooltip_quantity);

    }

    private void setTooltipText(View view, int resId) {
        TooltipCompat.setTooltipText(view, getString(resId));
    }

    private String getString(@StringRes int id) {
        return context.getString(id);
    }

}


