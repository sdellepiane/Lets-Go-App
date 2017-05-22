package com.letsgo.appletsgo.domain.interactor;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.FavoritesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackFavorites;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class FavoritesInteractor {
    private static final String TAG = "FavoritesInteractor";
    private FavoritesServiceRepository favoritesServiceRepository;

    public FavoritesInteractor(FavoritesServiceRepository favoritesServiceRepository) {
        this.favoritesServiceRepository = favoritesServiceRepository;
    }

    public void getFavoritesInteractor(final RequestCallBackFavorites requestCallBackFavorites){
        this.favoritesServiceRepository.getFavoritesRequest(new RequestCallBackFavorites() {
            @Override
            public void onRequestSuccess(Object object) {
                List<Actividades> actividadesList = (List<Actividades>) object;
                LogUtils.v(TAG, "tiendasList " + actividadesList.toString());
                requestCallBackFavorites.onRequestSuccess(actividadesList);
            }

            @Override
            public void onRequestFailure(Throwable e) {
                requestCallBackFavorites.onRequestFailure(e);
            }
        });
    }
}
