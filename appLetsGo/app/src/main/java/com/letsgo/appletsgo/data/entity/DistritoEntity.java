package com.letsgo.appletsgo.data.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 8/03/17.
 */

public class DistritoEntity implements Serializable {
    private String description;
    private String id_ubigeos;
    private boolean check;

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

    @Override
    public String toString() {
        return "Distrito{" +
                "description='" + description + '\'' +
                ", id_ubigeos='" + id_ubigeos + '\'' +
                ", check=" + check +
                '}';
    }
}
