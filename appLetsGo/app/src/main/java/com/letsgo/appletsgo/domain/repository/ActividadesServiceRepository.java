package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;

/**
 * Created by louislopez on 4/03/17.
 */

public interface ActividadesServiceRepository {
    void getActividadesRequest(ActividadesRaw raw, RequestCallBackActividades requestCallBackActividades);
    void getDetalleActividadesRequest(DetalleActividadRaw raw, RequestCallBackActividades requestCallBackActividades);
    void getDistritos(RequestCallBackActividades requestCallBackActividades);
}
