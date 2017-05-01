package com.letsgo.appletsgo.repository.datasource.categories;

import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.repository.Categories.RepositoryCallBackCategories;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface CategoriesPreferencesDataStore {
    void saveCategoriesToPreferences(List<Categories> categoriesList, RepositoryCallBackCategories repositoryCallBackCategories);
    void getCategoriesPreferences(RepositoryCallBackCategories repositoryCallBackCategories);



}
