package com.letsgo.appletsgo.data.entity.response;

import com.letsgo.appletsgo.data.entity.DistritoEntity;

import java.util.List;

/**
 * Created by louislopez on 13/03/17.
 */

public class DistritosResponse extends BaseResponse {
    private List<DistritoEntity> data;

    public List<DistritoEntity> getData() {
        return data;
    }

    public void setData(List<DistritoEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DistritosResponse{" +
                "data=" + data +
                '}';
    }
}
