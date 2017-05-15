package com.letsgo.appletsgo.repository.Categories;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.CategoriesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.mapper.ActividadesDataMapper;
import com.letsgo.appletsgo.data.mapper.CategoriesDataMapper;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.TypeCategoriesList;
import com.letsgo.appletsgo.domain.repository.ActividadesServiceRepository;
import com.letsgo.appletsgo.domain.repository.CategoriesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackCategories;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackPreferencesCategories;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesPreferencesDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesServiceDataStore;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class CategoriesDataRepository implements CategoriesServiceRepository {
    private static final String TAG = "CatalogDataRepository: ";
    private CategoriesDataStoreFactory categoriesDataStoreFactory;
    private CategoriesDataMapper categoriesDataMapper;
    //todo mapper

    public CategoriesDataRepository(CategoriesDataStoreFactory categoriesDataStoreFactory, CategoriesDataMapper categoriesDataMapper) {
        this.categoriesDataStoreFactory = categoriesDataStoreFactory;
        this.categoriesDataMapper = categoriesDataMapper;
    }

    @Override
    public void getCategoriesRequest(final RequestCallBackCategories requestCallBackCategories) {
        final CategoriesServiceDataStore categoriesServiceDataStore = (CategoriesServiceDataStore) this.categoriesDataStoreFactory.create(CategoriesDataStoreFactory.CLOUD);
        categoriesServiceDataStore.getCategories(new RepositoryCallBackCategories() {
            @Override
            public void onSuccess(Object object) {
                CategoriesResponse categoriesResponse = (CategoriesResponse) object;
                TypeCategoriesList typeCategoriesList =  categoriesDataMapper.transformTypeCategories(categoriesResponse.getData());
                LogUtils.v(TAG, typeCategoriesList.toString());
                requestCallBackCategories.onRequestSuccess(typeCategoriesList);
            }

            @Override
            public void onFailure(Throwable throwable) {
                requestCallBackCategories.onRequestFailure(null);
            }
        });
    }

    @Override
    public void saveCategoriesToPreferences(CategoriesToPreferences categoriesToPreferences, final RequestCallBackPreferencesCategories requestCallBackPreferencesCategories) {
        final CategoriesPreferencesDataStore categoriesServiceDataStore = (CategoriesPreferencesDataStore)this.categoriesDataStoreFactory.create(CategoriesDataStoreFactory.PREFERENCES);
        categoriesServiceDataStore.saveCategoriesToPreferences(categoriesToPreferences, new RepositoryCallBackCategories() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackPreferencesCategories.onSaveCategoriesPreferencesSuccess(object);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void getCategoriesToPreferences(final RequestCallBackPreferencesCategories requestCallBackPreferencesCategories) {
        final CategoriesPreferencesDataStore categoriesServiceDataStore = (CategoriesPreferencesDataStore)this.categoriesDataStoreFactory.create(CategoriesDataStoreFactory.PREFERENCES);
        categoriesServiceDataStore.getCategoriesPreferences(new RepositoryCallBackCategories() {
            @Override
            public void onSuccess(Object object) {
                requestCallBackPreferencesCategories.onGetCategoriesPreferencesSuccess(object);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
