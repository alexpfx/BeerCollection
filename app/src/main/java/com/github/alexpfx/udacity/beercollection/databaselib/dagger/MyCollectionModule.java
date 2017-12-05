package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.DefaultMyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.beer.detail.DefaultLoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 09/11/17.
 */

@Module
public class MyCollectionModule {


    private DrinkBeerView drinkBeerView;
    private MyCollectionView myCollectionView;

    public MyCollectionModule(DrinkBeerView drinkBeerView, MyCollectionView myCollectionView) {
        this.drinkBeerView = drinkBeerView;
        this.myCollectionView = myCollectionView;
    }

    public MyCollectionModule(DrinkBeerView drinkBeerView) {
        this.drinkBeerView = drinkBeerView;
    }


    @Provides
    @PerActivity
    public MyCollectionView myCollectionView(){
        return this.myCollectionView;
    }


    @Provides
    @PerActivity
    public DrinkBeerView drinkBeerView (){
        return drinkBeerView;
    }


    @Provides
    @PerActivity
    MyCollectionInteractor myCollectionInteractor (DefaultMyCollectionInteractor defaultMyCollectionInteractor){
        return defaultMyCollectionInteractor;
    }

    @Provides
    @PerActivity
    LoadCollectionPresenter myCollectionPresenter(LoadCollectionPresenterImpl defaultMyCollectionPresenter){
        return defaultMyCollectionPresenter;
    }


    @Provides
    @PerActivity
    DrinkBeerPresenter drinkBeerPresenter (DrinkBeerPresenterImpl drinkBeerPresenter){
        return drinkBeerPresenter;
    }

    @Provides
    @PerActivity
    DrinkBeerInteractor drinkBeerInteractor (DrinkBeerInteractorImpl drinkBeerInteractor){
        return drinkBeerInteractor;
    }

    @PerActivity
    @Provides
    LoadBeerInteractor detailInteractor(DefaultLoadBeerInteractor defaultDetailInteractor) {
        return defaultDetailInteractor;
    }


}
