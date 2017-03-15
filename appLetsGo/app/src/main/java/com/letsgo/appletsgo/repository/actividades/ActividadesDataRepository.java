package com.letsgo.appletsgo.repository.actividades;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class ActividadesDataRepository implements ActividadesServiceRepository {
    private static final String TAG = "CatalogDataRepository: ";
    private ActividadesDataStoreFactory actividadesDataStoreFactory;
    private ActividadesDataMapper actividadesDataMapper;
    //todo mapper

    public ActividadesDataRepository(ActividadesDataStoreFactory catalogDataRepository, ActividadesDataMapper catalogDataMapper) {
        actividadesDataStoreFactory = catalogDataRepository;
        this.actividadesDataMapper = catalogDataMapper;
    }

    @Override
    public void getActividadesRequest(ActividadesRaw raw, final RequestCallBackActividades requestCallBackActividades) {
        final ActividadesServiceDataStore actividadesServiceDataStore = this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
        actividadesServiceDataStore.getActividades(raw, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                List<Actividades> catalogList =  actividadesDataMapper.transFormActividadesWalkingService((ActividadesResponse) object);
                LogUtils.v(TAG, catalogList.toString());
                requestCallBackActividades.onRequestSuccess(catalogList, TypeService.LISTA_ACTIVIDAD);
            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void getDetalleActividadesRequest(DetalleActividadRaw raw, final RequestCallBackActividades requestCallBackActividades) {
        final ActividadesServiceDataStore actividadesServiceDataStore = this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
        actividadesServiceDataStore.getDetailActividad(raw, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                DetalleActividades detalleActividades =  actividadesDataMapper.transFormDetalleActividadWalkingService((DetalleActividadResponse) object);
                LogUtils.v(TAG, detalleActividades.toString());
                requestCallBackActividades.onRequestSuccess(detalleActividades,TypeService.DETALLE_ACTIVIDAD);
            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

    @Override
    public void getDistritos(final RequestCallBackActividades requestCallBackActividades) {
        final ActividadesServiceDataStore actividadesServiceDataStore = this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
        actividadesServiceDataStore.getDistritos(new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                List<Distrito> distritoList =  actividadesDataMapper.transFormDistritoWalkingService((DistritosResponse) object);
                LogUtils.v(TAG, distritoList.toString());
                requestCallBackActividades.onRequestSuccess(distritoList,TypeService.LISTA_DISTRITO);
            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }
}
