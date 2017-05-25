package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface FavoritesView extends BaseView {
    void showLoading();
    void hideLoading();
    void showFavorites(List<Actividades> favorites);
}
