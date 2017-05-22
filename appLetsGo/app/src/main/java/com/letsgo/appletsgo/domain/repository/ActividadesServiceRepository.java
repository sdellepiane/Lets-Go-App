package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface ActividadesServiceRepository {
    void getActividadesRequest(ActividadesRaw raw, RequestCallBackActividades requestCallBackActividades);
    void getDetalleActividadesRequest(DetalleActividadRaw raw, RequestCallBackActividades requestCallBackActividades);
    void getDistritos(RequestCallBackActividades requestCallBackActividades);
    void getCategoriesFromPreferences(RequestCallBackActividades requestCallBackActividades);
    void saveFavorite(Actividades actividades, RequestCallBackActividades requestCallBackActividades);
    void deleteFavorite(int idActividad, RequestCallBackActividades requestCallBackActividades);
    void getFavorite(int idFavorite, RequestCallBackActividades requestCallBackActividades);
    void assignFavorites(List<Actividades> actividadesList, RequestCallBackActividades requestCallBackActividades);
}
