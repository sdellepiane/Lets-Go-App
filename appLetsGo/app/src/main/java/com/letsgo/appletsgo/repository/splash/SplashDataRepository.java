package com.letsgo.appletsgo.repository.splash;

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
import com.letsgo.appletsgo.domain.repository.SplashServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.repository.Categories.RepositoryCallBackCategories;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesPreferencesDataStore;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class SplashDataRepository implements SplashServiceRepository {
    private static final String TAG = "CatalogDataRepository: ";
    private ActividadesDataStoreFactory actividadesDataStoreFactory;

    public SplashDataRepository(ActividadesDataStoreFactory catalogDataRepository) {
        actividadesDataStoreFactory = catalogDataRepository;
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
}
