package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.collection.DefaultMyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
import com.github.alexpfx.udacity.beercollection.beer.detail.DefaultLoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 09/11/17.
 */

@Module(includes = ActivityModule.class)
public class MyCollectionModule {


    private MyCollectionFragment fragment;

    public MyCollectionModule(){

    }

    public MyCollectionModule(MyCollectionFragment fragment) {
        this.fragment = fragment;
    }






    @Provides
    @PerActivity
    public MyCollectionView myCollectionView(){
        return fragment;
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



    @PerActivity
    @Provides
    LoadBeerInteractor detailInteractor(DefaultLoadBeerInteractor defaultDetailInteractor) {
        return defaultDetailInteractor;
    }


}
