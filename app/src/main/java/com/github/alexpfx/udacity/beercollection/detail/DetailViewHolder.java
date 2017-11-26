package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 07/11/17.
 */

public class DetailViewHolder {

    @BindView(R.id.img_label)
    ImageView imgLabel;
    @BindView(R.id.txt_ibu)
    TextView txtIbu;
    @BindView(R.id.txt_abv)
    TextView txtAbv;
    @BindView(R.id.txt_srm)
    TextView txtSrm;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_short_style)
    TextView txtShortStyle;
    @BindView(R.id.txt_description)
    TextView txtDescription;
    @BindView(R.id.txt_brewery)
    TextView txtBrewery;
    @BindView(R.id.txt_style)
    TextView txtStyle;
    @BindView(R.id.txt_style_category)
    TextView txtStyleCategory;
    @BindView(R.id.txt_serving_temperature)
    TextView txtServingTemperature;
    @BindView(R.id.txt_glass)
    TextView txtGlass;
    @BindView(R.id.btn_drink)
    ImageButton btnDrink;

    private Unbinder unbinder;
    private Context context;

    private PublishSubject<View> beerClickSubject = PublishSubject.create();


    public DetailViewHolder(View view) {
        context = view.getContext();
        unbinder = ButterKnife.bind(this, view);

        RxView.clicks(btnDrink).map(a -> btnDrink).subscribe
                (beerClickSubject);
    }

    public Observable<View> getBeerClickObservable() {
        return beerClickSubject.hide();
    }

    public void setBeer(Beer beer) {

        btnDrink.setTag(beer);
        txtIbu.setText(beer.getIbu());
        txtAbv.setText(beer.getAbv());
        txtSrm.setText(beer.getSrm());
        txtName.setText(beer.getName());
        txtShortStyle.setText(beer.getShortStyle());
        txtDescription.setText(beer.getDescription());
//        txtBrewery.setText();
        txtStyle.setText(beer.getStyle());
        txtStyleCategory.setText(beer.getStyleCategory());
        txtServingTemperature.setText(beer.getServingTemperature());
        txtGlass.setText(beer.getGlass());

        Picasso.with(context).load(beer.getLabelLarge()).resize(200,200).placeholder(R.drawable.beerplaceholder).centerCrop().into(imgLabel);

    }

    public void unbind() {

        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
