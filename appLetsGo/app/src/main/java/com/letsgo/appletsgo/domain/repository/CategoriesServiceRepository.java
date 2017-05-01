package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackCategories;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackPreferencesCategories;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface CategoriesServiceRepository {
    void getCategoriesRequest(RequestCallBackCategories requestCallBackCategories);
    void saveCategoriesToPreferences(List<Categories> categoriesList, RequestCallBackPreferencesCategories requestCallBackPreferencesCategories);
    void getCategoriesToPreferences(RequestCallBackPreferencesCategories requestCallBackPreferencesCategories);


}
