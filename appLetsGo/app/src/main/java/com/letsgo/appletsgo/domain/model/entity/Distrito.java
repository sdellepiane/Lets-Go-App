package com.letsgo.appletsgo.domain.model.entity;

import android.view.View;

import java.io.Serializable;

/**
 * Created by louislopez on 7/03/17.
 * "id_ubigeos": "150104",
 "description": "BARRANCO"
 */

public class Distrito implements Serializable {
    private String description;
    private String id_ubigeos;
    private boolean check = false;
    private int visible = View.GONE;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_ubigeos() {
        return id_ubigeos;
    }

    public void setId_ubigeos(String id_ubigeos) {
        this.id_ubigeos = id_ubigeos;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Distrito{" +
                "description='" + description + '\'' +
                ", id_ubigeos='" + id_ubigeos + '\'' +
                ", check=" + check +
                ", visible=" + visible +
                '}';
    }
}
