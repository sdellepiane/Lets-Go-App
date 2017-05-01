package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.TypeCategoriesList;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface CategoriesView extends BaseView {
    void getCategories(TypeCategoriesList typeCategoriesList);
    void showLoading();
    void hideLoading();
    void showEmptyCategories();
    void showMessageError(String message);
}
