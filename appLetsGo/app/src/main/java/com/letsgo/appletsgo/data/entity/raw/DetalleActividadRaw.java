package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;

/**
 * Created by louislopez on 6/03/17.
 */

public class DetalleActividadRaw implements Serializable {
    private String id_activities;

    public String getId_activities() {
        return id_activities;
    }

    public void setId_activities(String id_activities) {
        this.id_activities = id_activities;
    }

    @Override
    public String toString() {
        return "DetalleActividadRaw{" +
                "id_activities='" + id_activities + '\'' +
                '}';
    }
}
