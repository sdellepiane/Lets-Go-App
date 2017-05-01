package com.letsgo.appletsgo.data.entity.response;

import com.letsgo.appletsgo.data.entity.ActividadesEntity;
import com.letsgo.appletsgo.data.entity.TypesCategoriesListEntity;

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

public class CategoriesResponse extends BaseResponse {
    private TypesCategoriesListEntity data;

    public TypesCategoriesListEntity getData() {
        return data;
    }

    public void setData(TypesCategoriesListEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CategoriesResponse{" +
                "data=" + data +
                '}';
    }
}
