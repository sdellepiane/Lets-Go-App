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
import com.letsgo.appletsgo.repository.Categories.RepositoryCallBackCategories;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;
import com.letsgo.appletsgo.repository.datasource.actividades.DatabaseActividadesDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesPreferencesDataStore;

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
        final ActividadesServiceDataStore actividadesServiceDataStore = (ActividadesServiceDataStore) this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
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
                requestCallBackActividades.onRequestFailure(null, TypeService.LISTA_ACTIVIDAD);
            }
        });
    }

    @Override
    public void getDetalleActividadesRequest(DetalleActividadRaw raw, final RequestCallBackActividades requestCallBackActividades) {
        final ActividadesServiceDataStore actividadesServiceDataStore = (ActividadesServiceDataStore) this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
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
        final ActividadesServiceDataStore actividadesServiceDataStore = (ActividadesServiceDataStore) this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.CLOUD);
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

    @Override
    public void getCategoriesFromPreferences(final RequestCallBackActividades requestCallBackPreferencesCategories) {
        final CategoriesPreferencesDataStore categoriesServiceDataStore = (CategoriesPreferencesDataStore)this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.PREFERENCES);
        categoriesServiceDataStore.getCategoriesPreferences(new RepositoryCallBackCategories() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackPreferencesCategories.onCategoriesFromPreferencesRequestSuccess(object);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void saveFavorite(Actividades actividades, final RequestCallBackActividades requestCallBackActividades) {
        final DatabaseActividadesDataStore actividadesDatabaseDataStore = (DatabaseActividadesDataStore)this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.DB);
        actividadesDatabaseDataStore.saveFavorite(actividades, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackActividades.onSaveFavoriteSuccess(object);
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
    public void deleteFavorite(int idActividad, final RequestCallBackActividades requestCallBackActividades) {
        final DatabaseActividadesDataStore actividadesDatabaseDataStore = (DatabaseActividadesDataStore)this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.DB);
        actividadesDatabaseDataStore.deleteFavorite(idActividad, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackActividades.onDeleteFavoriteSuccess(object);
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
    public void getFavorite(int idFavorite, final RequestCallBackActividades requestCallBackActividades) {
        final DatabaseActividadesDataStore actividadesDatabaseDataStore = (DatabaseActividadesDataStore)this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.DB);
        actividadesDatabaseDataStore.getFavorite(idFavorite, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                //requestCallBackActividades.onGr(object);
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
    public void assignFavorites(List<Actividades> actividadesList, final RequestCallBackActividades requestCallBackActividades) {
        final DatabaseActividadesDataStore actividadesDatabaseDataStore = (DatabaseActividadesDataStore)this.actividadesDataStoreFactory.create(ActividadesDataStoreFactory.DB);
        actividadesDatabaseDataStore.assignFavorites(actividadesList, new RepositoryCallBackActividades() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackActividades.onAssignFavoriteSuccess(object);
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
