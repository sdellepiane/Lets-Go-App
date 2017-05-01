package com.letsgo.appletsgo.repository.datasource.categories;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.repository.Categories.RepositoryCallBackCategories;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

/**
 * Created by louislopez on 4/03/17.
 */

public interface CategoriesServiceDataStore {
    void getCategories(RepositoryCallBackCategories repositoryCallBackCategories);
    void saveCategoriesToPreferences(RepositoryCallBackCategories repositoryCallBackCategories);
}
