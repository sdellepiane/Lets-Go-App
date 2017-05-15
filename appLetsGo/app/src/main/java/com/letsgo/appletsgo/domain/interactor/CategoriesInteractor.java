package com.letsgo.appletsgo.domain.interactor;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
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

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class CategoriesInteractor {
    private static final String TAG = "CategoriesInteractor";
    private CategoriesServiceRepository categoriesServiceRepository;

    public CategoriesInteractor(CategoriesServiceRepository categoriesServiceRepository) {
        this.categoriesServiceRepository = categoriesServiceRepository;
    }

    public void listCategoriesInteractor(final RequestCallBackCategories requestCallBackCategories){
        this.categoriesServiceRepository.getCategoriesRequest(new RequestCallBackCategories() {

            @Override
            public void onRequestSuccess(Object object) {
                LogUtils.v(TAG, "tiendasList " + object.toString());
                requestCallBackCategories.onRequestSuccess(object);
            }

            @Override
            public void onRequestFailure(Throwable throwable) {
                requestCallBackCategories.onRequestFailure(throwable);
            }
        });
    }

    public void saveCategoriesToPreferences(CategoriesToPreferences categoriesToPreferences, final RequestCallBackPreferencesCategories requestCallBackPreferencesCategories){
        this.categoriesServiceRepository.saveCategoriesToPreferences(categoriesToPreferences, new RequestCallBackPreferencesCategories() {

            @Override
            public void onSaveCategoriesPreferencesSuccess(Object object) {
                LogUtils.v(TAG, "tiendasList " + object.toString());
                requestCallBackPreferencesCategories.onSaveCategoriesPreferencesSuccess(object);
            }

            @Override
            public void onGetCategoriesPreferencesSuccess(Object object) {

            }
        });
    }

    public void getCategoriesToPreferences(final RequestCallBackPreferencesCategories requestCallBackPreferencesCategories){
        this.categoriesServiceRepository.getCategoriesToPreferences(new RequestCallBackPreferencesCategories() {

            @Override
            public void onSaveCategoriesPreferencesSuccess(Object object) {
            }

            @Override
            public void onGetCategoriesPreferencesSuccess(Object object) {
                LogUtils.v(TAG, "tiendasList " + object.toString());
                requestCallBackPreferencesCategories.onGetCategoriesPreferencesSuccess(object);
            }
        });
    }
}
