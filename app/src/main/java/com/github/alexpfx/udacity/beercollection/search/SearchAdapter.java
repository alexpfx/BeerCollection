package com.github.alexpfx.udacity.beercollection.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.AbstractBaseAdapter;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 31/10/17.
 */
@PerActivity
public class SearchAdapter extends AbstractBaseAdapter<SearchAdapter.SeachViewHolder, Beer> {
    private static final String TAG = "SearchAdapter";
    private List<Beer> items = new ArrayList<>();
    private PublishSubject<View> clickDrink = PublishSubject.create();

    @Inject
    public SearchAdapter() {

    }

    public Observable<View> getClickDrinkObservable() {
        return clickDrink.hide();
    }

    @Override
    protected SeachViewHolder createViewHolder(View view) {
        return new SeachViewHolder(view);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_beer, parent, false);
//TODO: descompentar.
//        View btnView = view.findViewById(R.id.btn_drink);
//        RxView.clicks(btnView).map(a -> btnView).subscribe(clickDrink);


        return view;
    }

    @Override
    public void onBindViewHolder(SeachViewHolder holder, int position) {
        Beer beer = items.get(position);

        holder.bind(beer);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setItems(List<Beer> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class SeachViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;
        @BindView(R.id.txt_beer_name)
        TextView txtBeerName;
        @BindView(R.id.txt_beer_style)
        TextView txtBeerStyle;
        //        @BindView(R.id.btn_favorite)
//        ImageButton btnFavorite;
        @BindView(R.id.img_beer_label)
        ImageView imgBeerLabel;
        @BindView(R.id.txt_abv)
        TextView txtAbv;
        @BindView(R.id.txt_ibu)
        TextView txtIbu;
        @BindView(R.id.txt_srm)
        TextView txtSrm;


//        @BindView(R.id.btn_drink)
//        ImageButton btnDrink;


        private Context context;

        public SeachViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            context = itemView.getContext();
            this.itemView = itemView;
        }


        public void bind(Beer beer) {
            itemView.setTag(beer);
            txtBeerName.setText(beer.getName());
            txtBeerStyle.setText(beer.getStyle());
            setOrHide(context.getString(R.string.label_abv), txtAbv, beer.getAbv());
            setOrHide(context.getString(R.string.label_ibu), txtIbu, beer.getIbu());
            setOrHide(context.getString(R.string.label_srm), txtSrm, beer.getSrm());


            Picasso.with(context).load(beer.getLabelLarge())
                    .resize(320, 320)
                    .transform(new CropMiddleFirstPixelTransformation())
                    .centerCrop()
                    .into(imgBeerLabel);

        }

        private void setOrHide(String label, TextView view, Object o) {
            if (o == null) {
                view.setVisibility(View.GONE);
            } else {
                CharSequence text = TextUtils.concat(label, ": ", o.toString());
                view.setText(text);
            }
        }

    }

}
