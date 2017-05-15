package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface ActividadesView extends BaseView {
    void getActividades(List<Actividades> actividadesList);
    void detalleActividad(DetalleActividades detalleActividades);
    void getDistritos(List<Distrito> distritoList);
    void showLoading();
    void hideLoading();
    void showEmptyActivitys();
    void showMessageError(String message);
}
