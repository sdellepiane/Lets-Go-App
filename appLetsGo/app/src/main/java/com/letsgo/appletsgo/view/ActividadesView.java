package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface ActividadesView extends BaseView {
    void getActividades(List<Actividades> tiendaList);
    void detalleActividad(DetalleActividades detalleActividades);
    void getDistritos(List<Distrito> distritoList);
    void showLoading();
    void hideLoading();
    void showEmptyActivitys();
    void getCategoriesFromPreferences(CategoriesToPreferences categoriesToPreferences);
    void showMessageError(String message);
    void saveFavorite(Actividades actividades);
    void deleteFavorite(Actividades actividades, int actividadId);
    void assignFavorites(List<Actividades> actividadesList);
    void saveFavoriteSuccess();
    void deleteFavoriteSuccess();
}
