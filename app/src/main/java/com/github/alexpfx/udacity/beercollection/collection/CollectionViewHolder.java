package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.search.CropMiddleFirstPixelTransformation;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import io.reactivex.subjects.PublishSubject;

public class CollectionViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CollectionViewHolder";
    private final PublishSubject<View> clickDetailSubject;
    private final PublishSubject<View> clickAddBeerSubject;
    private final PublishSubject<View> clickHistorySubject;
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

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imageBeerLabel.setImageBitmap(bitmap);
            boolean usePallete = false;
            if (!usePallete) return;

            Palette.from(bitmap).generate(palette -> {
//                    Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

                Palette.Swatch vibrantSwatch = palette.getDominantSwatch();

//                    Palette.Swatch vibrantSwatch = palette.getDarkMutedSwatch();
//                    Palette.Swatch vibrantSwatch = palette.getLightMutedSwatch();


                if (vibrantSwatch != null) {
                    int rgbScrim = ColorUtils.setAlphaComponent(vibrantSwatch.getRgb(), (int) (255 * 0.2f));
                    int rgbTextBackground = ColorUtils.setAlphaComponent(vibrantSwatch.getRgb(), (int) (255 *
                            0.75f));

                    int textRgb = vibrantSwatch.getBodyTextColor();

//                        viewScrim.setBackgroundColor(rgbScrim);

                    textBeerName.setBackgroundColor(rgbTextBackground);
                    textBeerName.setTextColor(textRgb);

                    textLastDrinkDate.setBackgroundColor(rgbTextBackground);
                    textLastDrinkDate.setTextColor(textRgb);


                    textQuantity.setBackgroundColor(rgbTextBackground);
                    textQuantity.setTextColor(textRgb);


                }
            });

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    private Context context;


    public CollectionViewHolder(View itemView, PublishSubject<View> clickDetailSubject, PublishSubject<View>
            clickAddBeerSubject, PublishSubject<View> clickHistorySubject, PublishSubject<View> clickToggleSelectionSubject,
                                PublishSubject<View> longClickItemViewSubject
    ) {
        super(itemView);
        this.clickDetailSubject = clickDetailSubject;
        this.clickAddBeerSubject = clickAddBeerSubject;
        this.clickHistorySubject = clickHistorySubject;
        this.clickToggleSelectionSubject = clickToggleSelectionSubject;
        this.longClickItemViewSubject = longClickItemViewSubject;


        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        setupEvents();
    }


    public synchronized void bind(CollectionItem item, boolean isSelected, boolean isSelectable) {
        btnToggleSelection.setSelected(isSelected);
        btnToggleSelection.setVisibility(isSelectable? View.VISIBLE: View.INVISIBLE);
        Beer beer = item.getBeer();
        String id = beer.getId();
        btnDrink.setTag(id);

        setupLabelView(beer);

        setupBeerNameView(beer);

        setupQuantityView(item);

        setupLastDrinkDateView(item);
    }

    @OnLongClick(R.id.image_beer_label)
    public boolean onImageBeerLabelLongClick(View view) {
        longClickItemViewSubject.onNext(itemView);
        return true;
    }


    private void setupEvents() {

        //RxView.clicks(viewOverlay).map(a -> itemView).subscribe(clickToggleSelectionSubject);

        //RxView.clicks(imageBeerLabel).map(a -> imageBeerLabel).subscribe(clickDetailSubject);
        RxView.clicks(btnToggleSelection).map(a -> itemView).subscribe(clickToggleSelectionSubject);
        RxView.clicks(btnDrink).map(a -> btnDrink).subscribe(clickAddBeerSubject);
        RxView.clicks(textBeerName).map(a -> textBeerName).subscribe(clickHistorySubject);
        RxView.clicks(textLastDrinkDate).map(a -> textLastDrinkDate).subscribe(clickHistorySubject);
    }


    private void setupLabelView(Beer beer) {
        Picasso.with(context)
                .load(beer.getLabelLarge())
                .resize(512, 512)
                .transform(new CropMiddleFirstPixelTransformation())
                .placeholder(R.drawable.beerplaceholder)
                .centerCrop()
                .into(target);

    }


    private void setupBeerNameView(Beer beer) {
        textBeerName.setText(beer.getName());
        textBeerName.setTag(beer.getId());
        setTooltipText(textBeerName, R.string.tooltip_beer_name);

    }


    private void setupLastDrinkDateView(CollectionItem collectionItem) {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
        CharSequence dateFormated = dateInstance.format(collectionItem.getLastDate());
        textLastDrinkDate.setText(dateFormated);
        textLastDrinkDate.setTag(collectionItem.getBeer().getId());

        setTooltipText(textLastDrinkDate, R.string.tooltip_last_beer);


    }

    private void setupQuantityView(CollectionItem collectionItem) {
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


