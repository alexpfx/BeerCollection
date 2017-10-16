package com.github.alexpfx.udacity.beercollection;

import android.os.Bundle;
import android.util.Log;

import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.github.alexpfx.udacity.beercollection.domain.model.SearchBeerResponse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MainActivity extends BaseActivity {

    @Inject
    BreweryDBService service;

    public static final String KEY = "add it to an external project constant";


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Flowable<SearchBeerResponse> stella = service.searchBeers(KEY, "stella");

        FirebaseDatabase instance = FirebaseDatabase.getInstance();

        DatabaseReference reference = instance.getReference("message");

        reference.setValue("hello, world");



        Log.d(TAG, "onCreate: q: "+stella.count());

    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);

    }


}
