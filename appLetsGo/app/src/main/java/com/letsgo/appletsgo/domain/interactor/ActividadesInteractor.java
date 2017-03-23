package com.letsgo.appletsgo.domain.interactor;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class ActividadesInteractor {
    private static final String TAG = "ActividadesInteractor";
    private ActividadesServiceRepository actividadesServiceRepository;

    public ActividadesInteractor(ActividadesServiceRepository actividadesServiceRepository) {
        this.actividadesServiceRepository = actividadesServiceRepository;
    }

    public void listActividadesInteractor(final ActividadesRaw raw, final RequestCallBackActividades requestCallBackActividades){
        this.actividadesServiceRepository.getActividadesRequest(raw, new RequestCallBackActividades() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                List<Actividades> actividadesList = (List<Actividades>) object;
                LogUtils.v(TAG, "tiendasList " + actividadesList.toString());
                requestCallBackActividades.onRequestSuccess(actividadesList,type);
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

    public void detalleActividadInteractor(final DetalleActividadRaw raw, final RequestCallBackActividades requestCallBackActividades){
        this.actividadesServiceRepository.getDetalleActividadesRequest(raw, new RequestCallBackActividades() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                DetalleActividades detalleActividades = (DetalleActividades) object;
                LogUtils.v(TAG, "tiendasList " + detalleActividades.toString());
                requestCallBackActividades.onRequestSuccess(detalleActividades,type);

            }

            @Override
            public void onRequestFailure(Throwable e) {

            }

            @Override
            public void onRequestFailure(Throwable throwable, int type) {
                LogUtils.v(TAG, "onRequestFailure " + throwable + " ERROR: " + type);
                requestCallBackActividades.onRequestFailure(throwable,type);
            }
        });
    }

    public void distritos(final RequestCallBackActividades requestCallBackActividades){
        this.actividadesServiceRepository.getDistritos(new RequestCallBackActividades() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                List<Distrito> distritoList = (List<Distrito>) object;
                LogUtils.v(TAG, " distritos " + distritoList.toString());
                requestCallBackActividades.onRequestSuccess(distritoList,type);
            }

            @Override
            public void onRequestFailure(Throwable e) {

            }

            @Override
            public void onRequestFailure(Throwable throwable, int type) {
                LogUtils.v(TAG, "onRequestFailure " + throwable + " ERROR: " + type);
                requestCallBackActividades.onRequestFailure(throwable,type);
            }
        });
    }

}