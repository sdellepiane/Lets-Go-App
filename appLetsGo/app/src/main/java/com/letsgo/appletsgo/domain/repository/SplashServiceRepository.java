package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;

/**
 * Created by louislopez on 4/03/17.
 */

public interface SplashServiceRepository {
    void getCategoriesFromPreferences(RequestCallBackActividades requestCallBackActividades);
}
