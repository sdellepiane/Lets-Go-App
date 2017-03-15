package com.letsgo.appletsgo.data.entity.response;

import com.letsgo.appletsgo.data.entity.DetalleActividadesEntity;

import java.io.Serializable;

/**
 * Created by louislopez on 6/03/17.
 */

public class DetalleActividadResponse extends BaseResponse implements Serializable {
    private DetalleActividadesEntity data;

    public DetalleActividadesEntity getData() {
        return data;
    }

    public void setData(DetalleActividadesEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DetalleActividadResponse{" +
                "data=" + data +
                '}';
    }
}
