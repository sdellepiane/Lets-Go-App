package com.letsgo.appletsgo.repository.Favorites;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.repository.FavoritesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackFavorites;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.favorites.DatabaseFavoritesDataStore;
import com.letsgo.appletsgo.repository.datasource.favorites.FavoritesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.FavoritesServiceDataStore;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class FavoritesDataRepository implements FavoritesServiceRepository {
    private static final String TAG = "FavoritesDataRepository: ";
    private FavoritesDataStoreFactory favoritesDataStoreFactory;
    private ActividadesDataMapper actividadesDataMapper;
    //todo mapper

    public FavoritesDataRepository(FavoritesDataStoreFactory favoritesDataStoreFactory, ActividadesDataMapper catalogDataMapper) {
        this.favoritesDataStoreFactory = favoritesDataStoreFactory;
        this.actividadesDataMapper = catalogDataMapper;
    }

    @Override
    public void getFavoritesRequest(final RequestCallBackFavorites requestCallBackFavorites) {
        final DatabaseFavoritesDataStore databaseFavoritesDataStore = (DatabaseFavoritesDataStore) this.favoritesDataStoreFactory.create(ActividadesDataStoreFactory.DB);
        databaseFavoritesDataStore.getFavorites(new RepositoryCallBackFavorites() {
            @Override
            public void onSuccess(Object object) {
                List<Actividades> favoriteList =  (List<Actividades>) object;
                LogUtils.v(TAG, favoriteList.toString());
                requestCallBackFavorites.onRequestSuccess(favoriteList);
            }

            @Override
            public void onFailure(Throwable throwable) {
                requestCallBackFavorites.onRequestFailure(null);
            }
        });
    }
}
