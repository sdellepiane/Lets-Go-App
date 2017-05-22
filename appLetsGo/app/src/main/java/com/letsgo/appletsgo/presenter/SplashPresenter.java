package com.letsgo.appletsgo.presenter;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.domain.interactor.ActividadesInteractor;
import com.letsgo.appletsgo.domain.interactor.SplashInteractor;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.SplashServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.repository.actividades.ActividadesDataRepository;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.splash.SplashDataRepository;
import com.letsgo.appletsgo.view.ActividadesView;
import com.letsgo.appletsgo.view.SplashView;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class SplashPresenter implements Presenter<SplashView>, RequestCallBackActividades {
    private static final String TAG = "ActividadesPresenter";
    private SplashView splashView;
    private SplashInteractor splashInteractor;
    private SplashServiceRepository splashServiceRepository;

    public void getCategoriesFromPreferences(){
        splashInteractor.getCategoriesFromPreferences(this);
    }

    @Override
    public void onRequestSuccess(Object object) {

    }

    @Override
    public void onRequestSuccess(Object object, int type) {

    }

    @Override
    public void onCategoriesFromPreferencesRequestSuccess(Object object) {
        CategoriesToPreferences categoriesToPreferences = (CategoriesToPreferences) object;
        if(categoriesToPreferences != null){
            if(categoriesToPreferences.getCategoriesList().size() > 0){
                splashView.evaluateCategoriesFromPreferences(true);
            } else{
                splashView.evaluateCategoriesFromPreferences(false);
            }
        } else{
            splashView.evaluateCategoriesFromPreferences(false);
        }
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

    }

    @Override
    public void onRequestFailure(Throwable throwable, int type) {

    }

    @Override
    public void attachedView(SplashView view) {
        splashView = view;
        splashServiceRepository = new SplashDataRepository(new ActividadesDataStoreFactory(splashView.getContext()));
        splashInteractor = new SplashInteractor(splashServiceRepository);
    }

    @Override
    public void deatchView() {
        splashView = null;
    }
}
