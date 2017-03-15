package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 12/03/17.
 */

public class DistritosSession implements Serializable {
    private List<Distrito> distritoList;

    public List<Distrito> getDistritoList() {
        return distritoList;
    }

    public void setDistritoList(List<Distrito> distritoList) {
        this.distritoList = distritoList;
    }

    @Override
    public String toString() {
        return "DistritosSession{" +
                "distritoList=" + distritoList +
                '}';
    }
}
