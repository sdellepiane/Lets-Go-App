package com.letsgo.appletsgo.presenter;

import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.domain.interactor.FavoritesInteractor;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.repository.FavoritesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackFavorites;
import com.letsgo.appletsgo.repository.Favorites.FavoritesDataRepository;
import com.letsgo.appletsgo.repository.datasource.favorites.FavoritesDataStoreFactory;
import com.letsgo.appletsgo.view.FavoritesView;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class FavoritesPresenter implements Presenter<FavoritesView>, RequestCallBackFavorites {
    private static final String TAG = "ActividadesPresenter";
    private FavoritesView favoritesView;
    private FavoritesInteractor favoritesInteractor;
    private FavoritesServiceRepository favoritesServiceRepository;

    public void getFavorites(){
        favoritesView.showLoading();
        favoritesInteractor.getFavoritesInteractor(this);
    }

    @Override
    public void onRequestSuccess(Object object) {
        favoritesView.hideLoading();
        List<Actividades> favoritesList = (List<Actividades>) object;
        favoritesView.showFavorites(favoritesList);
    }

    @Override
    public void onRequestFailure(Throwable e) {
        favoritesView.hideLoading();
    }

    @Override
    public void attachedView(FavoritesView view) {
        favoritesView = view;
        favoritesServiceRepository = new FavoritesDataRepository(new FavoritesDataStoreFactory(view.getContext()), new ActividadesDataMapper());
        favoritesInteractor = new FavoritesInteractor(favoritesServiceRepository);
    }

    @Override
    public void deatchView() {
        favoritesView = null;
    }
}
