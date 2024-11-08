package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.collection.ClearCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.ClearCollectionPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.DeleteBeerPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.LoadCollectionPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionInteractorImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class MyCollectionModule {


    public MyCollectionModule() {

    }


    @Provides
    @PerActivity
    MyCollectionInteractor myCollectionInteractor(MyCollectionInteractorImpl myCollectionInteractorImpl) {
        return myCollectionInteractorImpl;
    }


    @Provides
    @PerActivity
    LoadCollectionPresenter myCollectionPresenter(LoadCollectionPresenterImpl defaultMyCollectionPresenter) {
        return defaultMyCollectionPresenter;
    }


    @PerActivity
    @Provides
    ClearCollectionPresenter clearCollectionPresenter(ClearCollectionPresenterImpl clearCollectionPresenter) {
        return clearCollectionPresenter;
    }


    @PerActivity
    @Provides
    DeleteBeerPresenter deleteBeerPresenter(DeleteBeerPresenterImpl deleteBeerPresenter) {
        return deleteBeerPresenter;
    }
}
