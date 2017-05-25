package com.letsgo.appletsgo.domain.interactor;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.SplashServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class SplashInteractor {
    private static final String TAG = "ActividadesInteractor";
    private SplashServiceRepository splashServiceRepository;

    public SplashInteractor(SplashServiceRepository splashServiceRepository) {
        this.splashServiceRepository = splashServiceRepository;
    }

    public void getCategoriesFromPreferences(final RequestCallBackActividades requestCallBackActividades){
        this.splashServiceRepository.getCategoriesFromPreferences(new RequestCallBackActividades() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {

            }

            @Override
            public void onCategoriesFromPreferencesRequestSuccess(Object object) {
                requestCallBackActividades.onCategoriesFromPreferencesRequestSuccess(object);
            }

            @Override
            public void onSaveFavoriteSuccess(Object object) {

            }

            @Override
            public void onDeleteFavoriteSuccess(Object object) {

            }

            @Override
            public void onAssignFavoriteSuccess(Object object) {

            }

            @Override
            public void onRequestFailure(Throwable e) {
                requestCallBackActividades.onRequestFailure(e);
            }

            @Override
            public void onRequestFailure(Throwable throwable, int type) {
                LogUtils.v(TAG, "onRequestFailure " + throwable + " ERRO: " + type);
                requestCallBackActividades.onRequestFailure(throwable,type);
            }
        });
    }

}
