package com.github.alexpfx.udacity.beercollection.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.dagger.SearchScope;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 31/10/17.
 */
@SearchScope
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SeachViewHolder> {

    private List<Beer> items = new ArrayList<>();

    @Inject
    public SearchAdapter() {

    }

    @Override
    public SeachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beer, parent, false);
        return new SeachViewHolder(view);

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

    public void clear (){
        items.clear();
        notifyDataSetChanged();
    }

    public void setItems(List<Beer> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class SeachViewHolder extends RecyclerView.ViewHolder {
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

        private Context context;

        public SeachViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);


        }

        public void bind(Beer beer) {
            txtBeerName.setText(beer.getName());
            txtBeerStyle.setText(beer.getStyle());
            setOrHide(context.getString(R.string.label_abv), txtAbv, beer.getAbv());
            setOrHide(context.getString(R.string.label_ibu), txtIbu, beer.getIbu());
            setOrHide(context.getString(R.string.label_srm), txtSrm, beer.getSrm());
            Picasso.with(context).load(beer.getLabelIcon()).resize(160, 160).centerCrop().into(imgBeerLabel);
        }

        private  void setOrHide(String label, TextView view, Object o){
            if (o == null){
                view.setVisibility(View.GONE);
            } else {
                CharSequence text = TextUtils.concat(label, ": ", o.toString());
                view.setText(text);
            }
        }
    }

}
