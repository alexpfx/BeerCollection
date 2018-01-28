package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


@PerActivity
public class DeleteBeerPresenterImpl implements DeleteBeerPresenter {

    private MyCollectionInteractor interactor;
    private SchedulerProvider provider;
    private DeleteBeerView view;
    private CompositeDisposable compositeDisposable;

    @Inject
    public DeleteBeerPresenterImpl(MyCollectionInteractor interactor, SchedulerProvider provider) {
        this.interactor = interactor;
        this.provider = provider;
    }

    @Override
    public void init(DeleteBeerView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unLoad() {
        compositeDisposable.dispose();
        this.view = null;
    }

    @Override
    public void deleteBeer(String beerId) {
        Single single = interactor.deleteBeer(beerId);


        Disposable disposable = single.subscribeOn(provider.computation()).observeOn(provider.mainThread()).subscribe
                (onSuccess -> view.showBeerDeleted(beerId));
        compositeDisposable.add(disposable);
    }


    @Override
    public void deleteBeers(List<String> beers) {
        List<Single<Void>> singles = new ArrayList<>();
        for (String beerId : beers) {
            singles.add(interactor.deleteBeer(beerId));
        }

        // Notifica a UI quando todas as exclusões são executadas
        Single.zip(singles, (Function<Object[], Object>) objects -> 0)
                .subscribe(o -> view.showBeersDeleted());

    }
}
