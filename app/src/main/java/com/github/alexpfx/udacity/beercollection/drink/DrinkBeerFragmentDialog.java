package com.github.alexpfx.udacity.beercollection.drink;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkBeerFragmentDialog extends DialogFragment {

    @BindView(R.id.input_quantity)
    NumberPicker inputQuantity;

    private Listener listener;


    public static DrinkBeerFragmentDialog getInstance(String beerId, Listener listener) {
        DrinkBeerFragmentDialog instance = getInstance(beerId);
        instance.listener = listener;
        return instance;
    }


    public static DrinkBeerFragmentDialog getInstance(String beerId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_BEER_ID, beerId);
        DrinkBeerFragmentDialog drinkBeerFragmentDialog = new DrinkBeerFragmentDialog();
        drinkBeerFragmentDialog.setArguments(bundle);
        return drinkBeerFragmentDialog;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        dismiss();
        listener = null;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.message_how_many_beers));

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_drink_dialog, null);
        ButterKnife.bind(this, view);
        builder.setView(view);

        inputQuantity.setMinValue(0);
        inputQuantity.setMaxValue(10);

        builder.setPositiveButton(R.string.ok, (dialogInterface, id) -> {
            if (listener != null) {
                listener.onPositiveClick(inputQuantity.getValue());
            }
        });

        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
            if (listener != null) {
                listener.onNegativeClick();
            }
        });

        return builder.create();
    }


    public interface Listener {

        void onPositiveClick(Integer quant);

        void onNegativeClick();
    }

    public static abstract class PositiveClickListener implements Listener {

        @Override
        public void onNegativeClick() {
        }
    }
}
