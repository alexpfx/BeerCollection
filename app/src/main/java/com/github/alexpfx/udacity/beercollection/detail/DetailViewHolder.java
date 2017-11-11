package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    private Unbinder unbinder;
    private Context context;

    public DetailViewHolder(View view) {
        context = view.getContext();
        unbinder = ButterKnife.bind(this, view);
    }

    public void unbind() {

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void setBeer(Beer beer) {

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

        Picasso.with(context).load(beer.getLabelLarge()).resize(1024, 1024).centerCrop().into(imgLabel);

    }
}
