package com.letsgo.appletsgo.presenter;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.domain.interactor.ActividadesInteractor;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.repository.actividades.ActividadesDataRepository;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.view.ActividadesView;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class ActividadesPresenter implements Presenter<ActividadesView>, RequestCallBackActividades {
    private static final String TAG = "ActividadesPresenter";
    private ActividadesView actividadesView;
    private ActividadesInteractor actividadesInteractor;
    private ActividadesServiceRepository actividadesServiceRepository;
    private ActividadesRaw raw;

    public void listCatalog(ActividadesRaw raw){
        if (raw != null){
            actividadesView.showLoading();
            actividadesInteractor.listActividadesInteractor(raw, this);
        }

    }

    public void detalleActividad(DetalleActividadRaw raw){
        if (raw != null){
            actividadesView.showLoading();
            actividadesInteractor.detalleActividadInteractor(raw, this);
        }
    }

    public void distritosDisponibles(){
        actividadesInteractor.distritos(this);
    }

    public void getCategoriesFromPreferences(){
        actividadesView.showLoading();
        actividadesInteractor.getCategoriesFromPreferences(this);
    }

    @Override
    public void onRequestSuccess(Object object) {

    }

    @Override
    public void onRequestSuccess(Object object, int type) {
        switch (type) {
            case TypeService.LISTA_ACTIVIDAD:
                actividadesView.hideLoading();
                LogUtils.v(TAG, "onLogInSuccess " + object);
                List<Actividades> catalogList= (List<Actividades>)object;
                if (catalogList != null) {
                    actividadesView.getActividades(catalogList);
                }
                break;

            case TypeService.DETALLE_ACTIVIDAD:
                actividadesView.hideLoading();
                LogUtils.v(TAG, "onLogInSuccess " + object);
                DetalleActividades detalleActividades = (DetalleActividades)object;
                actividadesView.detalleActividad(detalleActividades);
                break;
            case TypeService.LISTA_DISTRITO:
                LogUtils.v(TAG, "onLogInSuccess " + object);
                List<Distrito> distritoList = (List<Distrito>)object;
                actividadesView.getDistritos(distritoList);
                break;
        }

    }

    @Override
    public void onCategoriesFromPreferencesRequestSuccess(Object object) {
        actividadesView.hideLoading();
        CategoriesToPreferences categoriesToPreferences = (CategoriesToPreferences) object;
        actividadesView.getCategoriesFromPreferences(categoriesToPreferences);
    }

    @Override
    public void onRequestFailure(Throwable e) {

    }

    @Override
    public void onRequestFailure(Throwable throwable, int type) {
        actividadesView.hideLoading();
        actividadesView.showEmptyActivitys();
    }

    @Override
    public void attachedView(ActividadesView view) {
        actividadesView = view;
        actividadesServiceRepository = new ActividadesDataRepository(new ActividadesDataStoreFactory(actividadesView.getContext()), new ActividadesDataMapper());
        actividadesInteractor = new ActividadesInteractor(actividadesServiceRepository);
    }

    @Override
    public void deatchView() {
        actividadesView = null;
    }
}
