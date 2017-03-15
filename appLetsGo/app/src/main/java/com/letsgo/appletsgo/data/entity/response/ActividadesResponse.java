package com.letsgo.appletsgo.data.entity.response;

import com.letsgo.appletsgo.data.entity.ActividadesEntity;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 *  "id_activities": "34",
 "type": "Cine club",
 "activity": "Las malas intenciones",
 "schedul": "2017-03-29 19:30:00",
 "price": "0.00",
 "img": [
 {
 "id_activities_collections": "9",
 "path": "201702271115260.jpg"
 }
 ]
 */

public class ActividadesResponse extends BaseResponse {
    private List<ActividadesEntity> data;

    public List<ActividadesEntity> getData() {
        return data;
    }

    public void setData(List<ActividadesEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ActividadesResponse{" +
                "data=" + data +
                '}';
    }
}
